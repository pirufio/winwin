using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WinWin.Web.Models.Dto
{
    public class UserDTO
    {
        public int Id { get; set; }
        public string Email { get; set; }
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public DateTime FechaNacimiento { get; set; }
    }
}