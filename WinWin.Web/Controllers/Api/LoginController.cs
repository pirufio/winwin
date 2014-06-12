using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using System.Web.Security;
using WinWin.Web.Models;
using WinWin.Web.Models.Dto;
using WinWin.Web.Repository;

namespace WinWin.Web.Controllers.Api
{
    public class LoginController : ApiController
    {
        IRepository<Usuario> repo;

        public LoginController()
        {
            repo = new Repository<Usuario>(StructureMap.ObjectFactory.GetInstance<IUnitOfWork>().Session);
        }

        public LoginController(IRepository<Usuario> pRepo)
        {
            repo = pRepo;
        }

        [HttpPost]
        [AllowAnonymous]
        public HttpResponseMessage Post(UserLoginDTO user)
        {

            Usuario userValid = repo.FindBy(x => x.Password == user.Password && x.Email == user.Email);
            var responseError = new HttpResponseMessage(HttpStatusCode.Forbidden);

            if (userValid == null)
            {
                responseError.Content = new StringContent("Invalid Username or password");
                return responseError;
            }
            else
            {
                CreateSecurityCookie(userValid);
            }
            UserDTO dto = new UserDTO()
            {
                Apellido = userValid.Apellido,
                Email = userValid.Email,
                FechaNacimiento = userValid.FechaNacimiento,
                Nombre = userValid.Nombre
            };
            return Request.CreateResponse(HttpStatusCode.OK, dto);
        }

        public static void CreateSecurityCookie(Usuario user)
        {
            FormsAuthentication.SetAuthCookie(user.Email, true);
            HttpCookie authCookie = GetSecurityCookie();
            //authCookie.Expires = DateTime.Now.AddMinutes(5);
        }

        public static HttpCookie GetSecurityCookie()
        {
            string cookieName = FormsAuthentication.FormsCookieName;
            HttpCookie authCookie = HttpContext.Current.Request.Cookies[cookieName];
            return authCookie;
        }
    }
}
