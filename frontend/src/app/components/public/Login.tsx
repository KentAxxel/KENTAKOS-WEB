import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { ArrowLeft, Mail, Lock } from 'lucide-react';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    // TODO: Implement actual login logic with backend here
    // For now, let's assume successful login goes to admin panel
    console.log('Login attempt with:', { email, password });
    navigate('/admin');
  };

  return (
    <div className="min-h-screen flex bg-gray-50">
      {/* Left side: Branding / Image */}
      <div className="hidden lg:flex lg:w-1/2 relative bg-gradient-to-br from-[#D62828] to-[#F77F00] overflow-hidden">
        {/* Decorative elements */}
        <div className="absolute inset-0 bg-black/10"></div>
        <div className="absolute -bottom-32 -left-32 w-96 h-96 bg-white/10 rounded-full blur-3xl"></div>
        <div className="absolute top-20 right-20 w-72 h-72 bg-white/10 rounded-full blur-3xl"></div>
        
        <div className="relative z-10 flex flex-col justify-center items-center w-full h-full text-white p-12">
          <div className="w-24 h-24 bg-white rounded-2xl flex items-center justify-center mb-8 shadow-2xl transform hover:scale-105 transition-transform duration-300">
            <span className="text-5xl font-bold bg-clip-text text-transparent bg-gradient-to-br from-[#D62828] to-[#F77F00]">
              K
            </span>
          </div>
          <h1 className="text-5xl font-bold mb-4 text-center">Bienvenido de nuevo</h1>
          <p className="text-xl text-white/80 text-center max-w-md">
            Ingresa a tu cuenta para ordenar tus platillos favoritos y disfrutar de KENTAKITOS.
          </p>
        </div>
      </div>

      {/* Right side: Login Form */}
      <div className="w-full lg:w-1/2 flex items-center justify-center p-8 sm:p-12">
        <div className="w-full max-w-md">
          {/* Back to Home Link */}
          <Link 
            to="/" 
            className="inline-flex items-center space-x-2 text-gray-500 hover:text-[#D62828] mb-12 transition-colors group"
          >
            <ArrowLeft className="w-4 h-4 group-hover:-translate-x-1 transition-transform" />
            <span>Volver al inicio</span>
          </Link>

          {/* Form Header */}
          <div className="mb-10">
            <h2 className="text-3xl font-bold text-[#111111] mb-2">Iniciar Sesión</h2>
            <p className="text-gray-600">Por favor, ingresa tus credenciales.</p>
          </div>

          {/* Form */}
          <form onSubmit={handleLogin} className="space-y-6">
            <div className="space-y-2">
              <label className="text-sm font-semibold text-gray-700 block">Correo Electrónico</label>
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <Mail className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="block w-full pl-10 pr-3 py-3 border border-gray-200 rounded-xl focus:ring-2 focus:ring-[#D62828]/20 focus:border-[#D62828] transition-colors bg-white shadow-sm"
                  placeholder="ejemplo@correo.com"
                  required
                />
              </div>
            </div>

            <div className="space-y-2">
              <label className="text-sm font-semibold text-gray-700 block">Contraseña</label>
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <Lock className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="block w-full pl-10 pr-3 py-3 border border-gray-200 rounded-xl focus:ring-2 focus:ring-[#D62828]/20 focus:border-[#D62828] transition-colors bg-white shadow-sm"
                  placeholder="••••••••"
                  required
                />
              </div>
            </div>

            <div className="flex items-center justify-between">
              <div className="flex items-center">
                <input
                  id="remember-me"
                  name="remember-me"
                  type="checkbox"
                  className="h-4 w-4 text-[#D62828] focus:ring-[#D62828] border-gray-300 rounded"
                />
                <label htmlFor="remember-me" className="ml-2 block text-sm text-gray-600">
                  Recordarme
                </label>
              </div>

              <div className="text-sm">
                <a href="#" className="font-semibold text-[#D62828] hover:text-[#F77F00] transition-colors">
                  ¿Olvidaste tu contraseña?
                </a>
              </div>
            </div>

            <button
              type="submit"
              className="w-full bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white py-3 rounded-xl font-bold text-lg hover:shadow-lg transform hover:-translate-y-0.5 transition-all duration-200"
            >
              Ingresar
            </button>
          </form>

          {/* Sign Up Link */}
          <p className="mt-8 text-center text-sm text-gray-600">
            ¿No tienes una cuenta?{' '}
            <Link to="/register" className="font-bold text-[#D62828] hover:text-[#F77F00] transition-colors">
              Regístrate aquí
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}
