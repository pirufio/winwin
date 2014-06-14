using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace WinWin.Web.Models
{
    public class Usuario
    {
        public virtual int Id { get; set; }
        [Required]
        public virtual string Password { get; set; }
        [Required]
        [EmailAddress]
        [StringLength(255)]
        public virtual string Email { get; set; }
        [Required]
        public virtual string Nombre { get; set; }
        [Required]
        public virtual string Apellido { get; set; }

        public virtual DateTime FechaNacimiento { get; set; }
    }
}