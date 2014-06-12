using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WinWin.Web.Repository
{
    public interface IIntKeyedRepository<TEntity> : IRepository<TEntity> where TEntity : class
    {
        TEntity FindBy(int id);
    }
}