using FluentNHibernate.Mapping;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WinWin.Web.Models;

namespace WinWin.Web.Mappings
{
    public class UserMap : ClassMap<Usuario>
    {
        public UserMap()
        {
            Table("Usuarios");
            Id(x => x.Id).GeneratedBy.Identity();
            Map(x => x.FechaNacimiento);
            Map(x => x.Email);
            Map(x => x.Apellido);
            Map(x => x.Nombre);
            Map(x => x.Password);
        }
    }
}