using NHibernate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WinWin.Web.Repository
{
    public interface IUnitOfWork : IDisposable
    {
        ISession Session { get; }
        void Commit();
        void Rollback();
    }
}