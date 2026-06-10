import { Facebook, Instagram, Twitter, MapPin, Phone, Clock, Mail } from 'lucide-react';

export default function Footer() {
  return (
    <footer id="contacto" className="bg-[#111111] text-white pt-16 pb-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8 mb-12">
          {/* Brand */}
          <div>
            <div className="flex items-center space-x-2 mb-4">
              <div className="w-10 h-10 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center">
                <span className="text-white font-bold text-xl">K</span>
              </div>
              <span className="text-2xl font-bold">KENTAKITOS</span>
            </div>
            <p className="text-gray-400 mb-4">
              El mejor sabor de comida rápida en la ciudad. Calidad, rapidez y sabor inigualable.
            </p>
            <div className="flex space-x-4">
              <a href="#" className="w-10 h-10 bg-white/10 hover:bg-gradient-to-r hover:from-[#D62828] hover:to-[#F77F00] rounded-lg flex items-center justify-center transition-all">
                <Facebook className="w-5 h-5" />
              </a>
              <a href="#" className="w-10 h-10 bg-white/10 hover:bg-gradient-to-r hover:from-[#D62828] hover:to-[#F77F00] rounded-lg flex items-center justify-center transition-all">
                <Instagram className="w-5 h-5" />
              </a>
              <a href="#" className="w-10 h-10 bg-white/10 hover:bg-gradient-to-r hover:from-[#D62828] hover:to-[#F77F00] rounded-lg flex items-center justify-center transition-all">
                <Twitter className="w-5 h-5" />
              </a>
            </div>
          </div>

          {/* Quick Links */}
          <div>
            <h3 className="text-lg font-bold mb-4">Enlaces Rápidos</h3>
            <ul className="space-y-2">
              <li>
                <a href="#inicio" className="text-gray-400 hover:text-[#F77F00] transition-colors">
                  Inicio
                </a>
              </li>
              <li>
                <a href="#menu" className="text-gray-400 hover:text-[#F77F00] transition-colors">
                  Menú
                </a>
              </li>
              <li>
                <a href="#promociones" className="text-gray-400 hover:text-[#F77F00] transition-colors">
                  Promociones
                </a>
              </li>
              <li>
                <a href="#nosotros" className="text-gray-400 hover:text-[#F77F00] transition-colors">
                  Nosotros
                </a>
              </li>
            </ul>
          </div>

          {/* Contact */}
          <div>
            <h3 className="text-lg font-bold mb-4">Contacto</h3>
            <ul className="space-y-3">
              <li className="flex items-start space-x-3">
                <MapPin className="w-5 h-5 text-[#F77F00] flex-shrink-0 mt-0.5" />
                <span className="text-gray-400">Av. Principal #123, Centro, Ciudad</span>
              </li>
              <li className="flex items-center space-x-3">
                <Phone className="w-5 h-5 text-[#F77F00] flex-shrink-0" />
                <span className="text-gray-400">+52 (555) 123-4567</span>
              </li>
              <li className="flex items-center space-x-3">
                <Mail className="w-5 h-5 text-[#F77F00] flex-shrink-0" />
                <span className="text-gray-400">hola@kentakitos.com</span>
              </li>
            </ul>
          </div>

          {/* Hours */}
          <div>
            <h3 className="text-lg font-bold mb-4">Horario</h3>
            <div className="space-y-3">
              <div className="flex items-start space-x-3">
                <Clock className="w-5 h-5 text-[#F77F00] flex-shrink-0 mt-0.5" />
                <div className="text-gray-400">
                  <p className="font-semibold text-white">Lunes - Viernes</p>
                  <p>10:00 AM - 11:00 PM</p>
                </div>
              </div>
              <div className="flex items-start space-x-3">
                <Clock className="w-5 h-5 text-[#F77F00] flex-shrink-0 mt-0.5" />
                <div className="text-gray-400">
                  <p className="font-semibold text-white">Sábado - Domingo</p>
                  <p>9:00 AM - 12:00 AM</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Bottom Bar */}
        <div className="border-t border-white/10 pt-8 text-center text-gray-400">
          <p>&copy; 2026 KENTAKITOS. Todos los derechos reservados.</p>
        </div>
      </div>
    </footer>
  );
}
