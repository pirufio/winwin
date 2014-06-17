using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WinWin.Web.Models;
using WinWin.Web.Repository;

namespace WinWin.Web.Controllers.Api
{
    public class UserController : ApiController
    {

         IRepository<Usuario> repo;

        public UserController()
        {
            repo = new Repository<Usuario>(StructureMap.ObjectFactory.GetInstance<IUnitOfWork>().Session);
        }

        public UserController(IRepository<Usuario> pRepo)
        {
            repo = pRepo;
        }

        // GET api/user
        public List<Usuario> Get()
        {
            List<Usuario> usuarios = new List<Usuario>();
            usuarios = repo.All().ToList();
            return usuarios;
        }

        // GET api/user/5
        public Usuario Get(int id)
        {
            Usuario usuario = repo.All().FirstOrDefault(x => x.Id == id);
            return usuario;
        }

        // POST api/user
        public HttpResponseMessage Post(Usuario usuario)
        {
            if (ModelState.IsValid)
            {
                usuario.FechaNacimiento = DateTime.Now;
                usuario.Nombre = "";
                usuario.Apellido = "";
                repo.Add(usuario);
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState); 
            }

            return Request.CreateResponse(HttpStatusCode.OK);
        }

        // PUT api/user/5
        public void Put(Usuario usuario)
        {
            if (ModelState.IsValid)
            {
                repo.Update(usuario);
            }
            else
            {
                Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }

        }

        // DELETE api/user/5
        public void Delete(int id)
        {
        }
    }
}
