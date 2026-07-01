import { BrowserRouter, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { Toaster } from 'sonner';
import Navbar from './components/public/Navbar';
import Hero from './components/public/Hero';
import Menu from './components/public/Menu';
import Promotions from './components/public/Promotions';
import About from './components/public/About';
import Footer from './components/public/Footer';
import Sidebar from './components/admin/Sidebar';
import Topbar from './components/admin/Topbar';
import Dashboard from './components/admin/Dashboard';
import Users from './components/admin/Users';
import Roles from './components/admin/Roles';
import Permissions from './components/admin/Permissions';
import Tables from './components/admin/Tables';
import Dishes from './components/admin/Dishes';
import Inventory from './components/admin/Inventory';
import Sales from './components/admin/Sales';
import Reports from './components/admin/Reports';
import Orders from './components/admin/Orders';
import Settings from './components/admin/Settings';
import { GoogleOAuthProvider } from '@react-oauth/google';
import Login from './components/public/Login';
import Register from './components/public/Register';

// Public Landing Page
function PublicPage() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <Hero />
      <Menu />
      <Promotions />
      <About />
      <Footer />
    </div>
  );
}

import { useEffect } from 'react';

// Admin Layout
function AdminLayout({ children }: { children: React.ReactNode }) {
  const location = useLocation();
  const userString = localStorage.getItem('user');
  const user = userString ? JSON.parse(userString) : null;

  // Si no está logueado, mandarlo al login
  if (!user) {
    return <Navigate to="/login" replace />;
  }

  // Heartbeat para mantener la sesión viva (cada 1 minuto)
  useEffect(() => {
    if (!user || !user.id || !user.sessionToken) return;

    const pingHeartbeat = async () => {
      try {
        await fetch(`https://shop.spring.informaticapp.com/api/auth/heartbeat?userId=${encodeURIComponent(user.encryptedId)}&sessionToken=${user.sessionToken}`, {
          //await fetch(`http://localhost:2920/api/auth/heartbeat?userId=${encodeURIComponent(user.encryptedId)}&sessionToken=${user.sessionToken}`, {
          method: 'POST'
        });
      } catch (error) {
        console.error('Error enviando heartbeat:', error);
      }
    };

    // Ping inmediato al entrar
    pingHeartbeat();

    // Ping cada 60 segundos
    const interval = setInterval(pingHeartbeat, 60000);
    return () => clearInterval(interval);
  }, [user?.id, user?.sessionToken]);

  // Si está logueado pero no tiene rol, solo puede ver el Dashboard (que muestra el mensaje de restricción)
  const isRestrictedUser = !user.role || user.role === 'Sin Rol';
  if (isRestrictedUser && location.pathname !== '/admin') {
    return <Navigate to="/admin" replace />;
  }

  // Validación de permisos dinámicos (basados en la base de datos)
  if (user.role !== 'ADMIN') {
    const userPerms = user.permisos || [];

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

    const requiredPerm = routePermissions[location.pathname];
    if (requiredPerm && !userPerms.includes(requiredPerm)) {
      return <Navigate to="/admin" replace />;
    }
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Sidebar />
      <div className="ml-64 min-h-screen flex flex-col">
        <Topbar />
        <main className="flex-1 p-8">
          {children}
        </main>
      </div>
    </div>
  );
}

export default function App() {
  return (
    <GoogleOAuthProvider clientId="527408127768-gina8rmjvj737t9v0n2hljhnk4c56j1o.apps.googleusercontent.com">
      <Toaster richColors position="top-right" />
      <BrowserRouter basename="/kentakitos/">
        <Routes>
          {/* Public Routes */}
          <Route path="/" element={<PublicPage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />

          {/* Admin Routes */}
          <Route path="/admin" element={<AdminLayout><Dashboard /></AdminLayout>} />
          <Route path="/admin/users" element={<AdminLayout><Users /></AdminLayout>} />
          <Route path="/admin/roles" element={<AdminLayout><Roles /></AdminLayout>} />
          <Route path="/admin/permissions" element={<AdminLayout><Permissions /></AdminLayout>} />
          <Route path="/admin/tables" element={<AdminLayout><Tables /></AdminLayout>} />
          <Route path="/admin/dishes" element={<AdminLayout><Dishes /></AdminLayout>} />
          <Route path="/admin/inventory" element={<AdminLayout><Inventory /></AdminLayout>} />
          <Route path="/admin/sales" element={<AdminLayout><Sales /></AdminLayout>} />
          <Route path="/admin/reports" element={<AdminLayout><Reports /></AdminLayout>} />
          <Route path="/admin/orders" element={<AdminLayout><Orders /></AdminLayout>} />
          <Route path="/admin/settings" element={<AdminLayout><Settings /></AdminLayout>} />
        </Routes>
      </BrowserRouter>
    </GoogleOAuthProvider>
  );
}