using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WinWin.Web.Models
{
    public class Usuario
    {
        public virtual int Id { get; set; }
        public virtual string Password { get; set; }
        public virtual string Email { get; set; }
        public virtual string Nombre { get; set; }
        public virtual string Apellido { get; set; }
        public virtual DateTime FechaNacimiento { get; set; }
    }
}