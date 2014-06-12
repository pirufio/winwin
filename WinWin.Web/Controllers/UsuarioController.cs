using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WinWin.Web.Models;
using WinWin.Web.Repository;

namespace WinWin.Web.Controllers
{
    public class UsuarioController : Controller
    {
        IRepository<Usuario> repo;

        public UsuarioController()
        {
            repo = new Repository<Usuario>(StructureMap.ObjectFactory.GetInstance<IUnitOfWork>().Session);
        }

        public UsuarioController(IRepository<Usuario> pRepo)
        {
            repo = pRepo;
        }

        //
        // GET: /User/

        public ActionResult Index()
        {
            var users = repo.All().ToList();
            return View(users);
        }


        //
        // GET: /User/Details/5

        public ActionResult Details(int id)
        {
            var user = repo.FindBy(x => x.Id == id);
            return View(user);
        }

        //
        // GET: /User/Create

        public ActionResult Create()
        {
            var user = new Usuario();
            return View(user);
        }

        //
        // POST: /User/Create

        [HttpPost]
        public ActionResult Create(Usuario user)
        {
            
            try
            {
                if (ModelState.IsValid)
                {
                    repo.Add(user);
                    return RedirectToAction("Index");
                }
                else
                {
                    return View(user);
                }
            }
            catch
            {
                return View(user);
            }

           
        }

        //
        // GET: /User/Edit/5

        public ActionResult Edit(int id)
        {
            var user = repo.FindBy(x => x.Id == id);
            return View(user);
        }

        //
        // POST: /User/Edit/5

        [HttpPost]
        public ActionResult Edit(int id, Usuario user)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    repo.Update(user);
                    return RedirectToAction("Index");
                }
                else
                {
                    return View(user);
                }
            }
            catch
            {
                return View(user);
            }
        }

        //
        // GET: /User/Delete/5

        public ActionResult Delete(int id)
        {
            try
            {
                var userInstance = repo.FindBy(x => x.Id == id);
                repo.Delete(userInstance);
                return RedirectToAction("Index");
            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}
