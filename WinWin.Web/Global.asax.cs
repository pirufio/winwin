using StructureMap.Pipeline;
using System;
using System.Web.Http;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;
using WinWin.Web.Repository;
using StructureMap.Web;
using StructureMap.Web.Pipeline;

namespace WinWin.Web
{
    // Note: For instructions on enabling IIS6 or IIS7 classic mode, 
    // visit http://go.microsoft.com/?LinkId=9394801

    public class WebApiApplication : System.Web.HttpApplication
    {
        public override void Init()
        {
            base.Init();
            EndRequest += new EventHandler(WebApiApplication_EndRequest);
            BeginRequest += new EventHandler(WebApiApplication_BeginRequest);
        }

        protected void Application_Start()
        {
            AreaRegistration.RegisterAllAreas();

            WebApiConfig.Register(GlobalConfiguration.Configuration);
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);

            StructureMap.ObjectFactory.Initialize(x =>
            {
                //var connectionString = "sqliteConnection";
                var connectionString = "sqlServerConnection";
                NHibernateHelper nHibernateHelper = new NHibernateHelper(connectionString);
                // ISessionFactory is expensive to initialize, so create it as a singleton.
                x.For<NHibernate.ISessionFactory>()
                    .Singleton()
                    .Use(nHibernateHelper.SessionFactory);

                x.For<IUnitOfWork>().HybridHttpOrThreadLocalScoped()
                    .Use(() => new UnitOfWork(StructureMap.ObjectFactory.GetInstance<NHibernate.ISessionFactory>()
                ));

            });
            BootstrapSupport.BootstrapBundleConfig.RegisterBundles(System.Web.Optimization.BundleTable.Bundles);
            BootstrapMvcSample.ExampleLayoutsRouteConfig.RegisterRoutes(RouteTable.Routes);
        }

        void WebApiApplication_EndRequest(object sender, EventArgs e)
        {
            using (IUnitOfWork unitOfWork = StructureMap.ObjectFactory.GetInstance<IUnitOfWork>())
            {
                try
                {
                    unitOfWork.Commit();
                }
                catch (Exception)
                {
                    unitOfWork.Rollback();
                }
                
            }

            HttpContextLifecycle.DisposeAndClearAll();
        }

        void WebApiApplication_BeginRequest(object sender, EventArgs e)
        {

        }
    }
}