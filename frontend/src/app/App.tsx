import { BrowserRouter, Routes, Route } from 'react-router-dom';
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

// Admin Layout
function AdminLayout({ children }: { children: React.ReactNode }) {
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