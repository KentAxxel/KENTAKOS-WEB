import { Bell, Search, User } from 'lucide-react';

export default function Topbar() {
  const userString = localStorage.getItem('user');
  const user = userString ? JSON.parse(userString) : null;
  const displayRole = (!user?.role || user?.role === 'Sin Rol') ? 'Usuario' : user.role;
  const displayName = user?.name || 'Usuario';

  return (
    <header className="h-16 bg-white border-b border-gray-200 flex items-center justify-between px-6">
      {/* Search */}
      <div className="flex-1 max-w-xl">
        <div className="relative">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
          <input
            type="text"
            placeholder="Buscar..."
            className="w-full pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
          />
        </div>
      </div>

      {/* Right Section */}
      <div className="flex items-center space-x-4">
        {/* Notifications */}
        <button className="relative p-2 hover:bg-gray-100 rounded-lg transition-colors">
          <Bell className="w-5 h-5 text-gray-600" />
          <span className="absolute top-1 right-1 w-2 h-2 bg-[#D62828] rounded-full"></span>
        </button>

        {/* User Profile */}
        <button className="flex items-center space-x-3 hover:bg-gray-100 rounded-lg px-3 py-2 transition-colors">
          <div className="w-8 h-8 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-full flex items-center justify-center text-white font-bold">
            {user?.avatar && user.avatar !== '👤' ? user.avatar : <User className="w-4 h-4 text-white" />}
          </div>
          <div className="text-left hidden md:block">
            <p className="text-sm font-semibold text-[#111111]">{displayName}</p>
            <p className="text-xs text-gray-500 capitalize">{displayRole.toLowerCase()}</p>
          </div>
        </button>
      </div>
    </header>
  );
}
