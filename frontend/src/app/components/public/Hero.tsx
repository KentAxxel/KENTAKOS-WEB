import { ArrowRight, Sparkles } from 'lucide-react';

export default function Hero() {
  return (
    <section id="inicio" className="relative min-h-screen flex items-center justify-center bg-gradient-to-br from-[#111111] via-[#1a1a1a] to-[#111111] text-white pt-16">
      {/* Background Pattern */}
      <div className="absolute inset-0 opacity-5">
        <div className="absolute inset-0" style={{
          backgroundImage: 'radial-gradient(circle, white 1px, transparent 1px)',
          backgroundSize: '50px 50px'
        }}></div>
      </div>

      <div className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20 text-center">
        {/* Badge */}
        <div className="inline-flex items-center space-x-2 bg-white/10 backdrop-blur-sm px-4 py-2 rounded-full mb-8 border border-white/20">
          <Sparkles className="w-4 h-4 text-[#F77F00]" />
          <span className="text-sm">El Mejor Sabor de la Ciudad</span>
        </div>

        {/* Main Heading */}
        <h1 className="text-5xl md:text-7xl font-bold mb-6 leading-tight">
          Bienvenido a
          <span className="block mt-2 bg-gradient-to-r from-[#D62828] via-[#F77F00] to-[#D62828] bg-clip-text text-transparent">
            KENTAKITOS
          </span>
        </h1>

        {/* Subtitle */}
        <p className="text-xl md:text-2xl text-gray-300 mb-12 max-w-3xl mx-auto">
          Donde cada bocado es una experiencia única. Pollo crujiente, hamburguesas jugosas y sabores que conquistan.
        </p>

        {/* CTA Buttons */}
        <div className="flex flex-col sm:flex-row gap-4 justify-center items-center">
          <a
            href="#menu"
            className="group bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-8 py-4 rounded-lg hover:shadow-2xl hover:shadow-[#D62828]/50 transition-all flex items-center space-x-2 text-lg"
          >
            <span>Ver Menú</span>
            <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform" />
          </a>
          <a
            href="#promociones"
            className="bg-white/10 backdrop-blur-sm border border-white/20 text-white px-8 py-4 rounded-lg hover:bg-white/20 transition-all text-lg"
          >
            Promociones Especiales
          </a>
        </div>

        {/* Hero Image Placeholder */}
        <div className="mt-16 relative">
          <div className="w-full h-96 bg-gradient-to-br from-[#D62828]/20 to-[#F77F00]/20 rounded-3xl border border-white/10 flex items-center justify-center overflow-hidden">
            <div className="text-center">
              <div className="text-8xl mb-4">🍗🍔🍟</div>
              <p className="text-gray-400">Comida Deliciosa Te Espera</p>
            </div>
          </div>
          {/* Decorative Elements */}
          <div className="absolute -top-10 -right-10 w-40 h-40 bg-[#D62828] rounded-full blur-3xl opacity-20"></div>
          <div className="absolute -bottom-10 -left-10 w-40 h-40 bg-[#F77F00] rounded-full blur-3xl opacity-20"></div>
        </div>
      </div>
    </section>
  );
}
