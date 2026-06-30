import { Link, useLocation } from 'react-router-dom';
import {
  LayoutDashboard,
  Users,
  Shield,
  Lock,
  Utensils,
  Package,
  ShoppingCart,
  FileText,
  ClipboardList,
  Settings,
  TableProperties,
  LogOut,
  ChevronLeft
} from 'lucide-react';
import { useState } from 'react';

const menuItems = [
  { icon: LayoutDashboard, label: 'Dashboard', path: '/admin' },
  { icon: Users, label: 'Usuarios', path: '/admin/users' },
  { icon: Shield, label: 'Roles', path: '/admin/roles' },
  { icon: Lock, label: 'Permisos', path: '/admin/permissions' },
  { icon: TableProperties, label: 'Mesas', path: '/admin/tables' },
  { icon: Utensils, label: 'Platillos', path: '/admin/dishes' },
  { icon: Package, label: 'Inventario', path: '/admin/inventory' },
  { icon: ShoppingCart, label: 'Ventas', path: '/admin/sales' },
  { icon: FileText, label: 'Reportes', path: '/admin/reports' },
  { icon: ClipboardList, label: 'Pedidos', path: '/admin/orders' },
  { icon: Settings, label: 'Configuración', path: '/admin/settings' },
];

export default function Sidebar() {
  const location = useLocation();
  const [isCollapsed, setIsCollapsed] = useState(false);
  const userString = localStorage.getItem('user');
  const user = userString ? JSON.parse(userString) : null;

  const handleLogout = async () => {
    if (user) {
      try {
        // await fetch(`https://shop.spring.informaticapp.com/api/auth/logout?userId=${encodeURIComponent(user.encryptedId)}`, { method: 'POST' });
        await fetch(`http://localhost:2920/api/auth/logout?userId=${encodeURIComponent(user.encryptedId)}`, { method: 'POST' });
      } catch (error) {
        console.error('Error al cerrar sesión en el servidor:', error);
      }
    }
    localStorage.removeItem('user');
  };

  return (
    <aside
      className={`fixed left-0 top-0 h-screen bg-[#111111] text-white transition-all duration-300 z-40 ${
        isCollapsed ? 'w-20' : 'w-64'
      }`}
    >
      {/* Header */}
      <div className="h-16 flex items-center justify-between px-4 border-b border-white/10">
        {!isCollapsed && (
          <div className="flex items-center space-x-2">
            <div className="w-8 h-8 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center">
              <span className="text-white font-bold">K</span>
            </div>
            <span className="font-bold text-lg">KENTAKITOS</span>
          </div>
        )}
        <button
          onClick={() => setIsCollapsed(!isCollapsed)}
          className="p-2 hover:bg-white/10 rounded-lg transition-colors"
        >
          <ChevronLeft
            className={`w-5 h-5 transition-transform ${isCollapsed ? 'rotate-180' : ''}`}
          />
        </button>
      </div>

      {/* Menu Items */}
      <nav className="p-4 space-y-2 overflow-y-auto h-[calc(100vh-8rem)]">
        {user?.role && user.role !== 'Sin Rol' ? (
          menuItems.filter(item => {
            if (user.role === 'ADMIN') return true;
            
            const userPerms = user.permisos || [];
            
            // Mapeo de rutas a permisos
            const routePermissions: Record<string, string> = {
              '/admin/users': 'GESTIONAR_USUARIOS',
              '/admin/roles': 'GESTIONAR_USUARIOS',
              '/admin/permissions': 'GESTIONAR_USUARIOS',
              '/admin/settings': 'GESTIONAR_USUARIOS',
              '/admin/tables': 'VER_MESAS',
              '/admin/inventory': 'VER_INVENTARIO',
              '/admin/dishes': 'VER_INVENTARIO',
              '/admin/sales': 'VER_CAJA',
              '/admin/orders': 'VER_CAJA',
              '/admin/reports': 'VER_REPORTES'
            };

            const requiredPerm = routePermissions[item.path];
            if (!requiredPerm) return true; // Rutas sin permiso específico (ej. Dashboard)
            
            return userPerms.includes(requiredPerm);
          }).map((item) => {
            const isActive = location.pathname === item.path;
            return (
              <Link
                key={item.path}
                to={item.path}
                className={`flex items-center space-x-3 px-4 py-3 rounded-lg transition-all ${
                  isActive
                    ? 'bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white shadow-lg'
                    : 'text-gray-400 hover:bg-white/10 hover:text-white'
                }`}
                title={isCollapsed ? item.label : ''}
              >
                <item.icon className="w-5 h-5 flex-shrink-0" />
                {!isCollapsed && <span>{item.label}</span>}
              </Link>
            );
          })
        ) : (
          <div className="text-gray-500 text-sm p-4 text-center">
            {!isCollapsed && "No tienes permisos"}
          </div>
        )}
      </nav>

      {/* Footer */}
      <div className="absolute bottom-0 left-0 right-0 p-4 border-t border-white/10">
        <Link
          to="/"
          onClick={handleLogout}
          className="flex items-center space-x-3 px-4 py-3 text-gray-400 hover:bg-white/10 hover:text-white rounded-lg transition-all"
          title={isCollapsed ? 'Cerrar Sesión' : ''}
        >
          <LogOut className="w-5 h-5 flex-shrink-0" />
          {!isCollapsed && <span>Cerrar Sesión</span>}
        </Link>
      </div>
    </aside>
  );
}
