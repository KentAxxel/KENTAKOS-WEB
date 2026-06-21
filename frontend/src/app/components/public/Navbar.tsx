import { Link, useNavigate } from 'react-router-dom';
import { Menu, X, ShoppingCart } from 'lucide-react';
import { useState } from 'react';

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

  return (
    <nav className="fixed top-0 left-0 right-0 bg-white/95 backdrop-blur-sm shadow-sm z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <Link to="/" className="flex items-center space-x-2">
            <div className="w-10 h-10 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center">
              <span className="text-white font-bold text-xl">K</span>
            </div>
            <span className="text-2xl font-bold text-[#111111]">KENTAKITOS</span>
          </Link>

          {/* Desktop Menu */}
          <div className="hidden md:flex items-center space-x-8">
            <a href="#inicio" className="text-[#111111] hover:text-[#D62828] transition-colors">
              Inicio
            </a>
            <a href="#menu" className="text-[#111111] hover:text-[#D62828] transition-colors">
              Menú
            </a>
            <a href="#promociones" className="text-[#111111] hover:text-[#D62828] transition-colors">
              Promociones
            </a>
            <a href="#nosotros" className="text-[#111111] hover:text-[#D62828] transition-colors">
              Nosotros
            </a>
            <a href="#contacto" className="text-[#111111] hover:text-[#D62828] transition-colors">
              Contacto
            </a>
            <button
              onClick={() => navigate('/login')}
              className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-2 rounded-lg hover:shadow-lg transition-all flex items-center space-x-2"
            >
              <ShoppingCart className="w-4 h-4" />
              <span>Ordenar Ahora</span>
            </button>
          </div>

          {/* Mobile Menu Button */}
          <button
            onClick={() => setIsOpen(!isOpen)}
            className="md:hidden text-[#111111]"
          >
            {isOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
          </button>
        </div>

        {/* Mobile Menu */}
        {isOpen && (
          <div className="md:hidden py-4 space-y-4">
            <a href="#inicio" className="block text-[#111111] hover:text-[#D62828] transition-colors">
              Inicio
            </a>
            <a href="#menu" className="block text-[#111111] hover:text-[#D62828] transition-colors">
              Menú
            </a>
            <a href="#promociones" className="block text-[#111111] hover:text-[#D62828] transition-colors">
              Promociones
            </a>
            <a href="#nosotros" className="block text-[#111111] hover:text-[#D62828] transition-colors">
              Nosotros
            </a>
            <a href="#contacto" className="block text-[#111111] hover:text-[#D62828] transition-colors">
              Contacto
            </a>
            <button
              onClick={() => navigate('/login')}
              className="w-full bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-2 rounded-lg hover:shadow-lg transition-all"
            >
              Ordenar Ahora
            </button>
          </div>
        )}
      </div>
    </nav>
  );
}
