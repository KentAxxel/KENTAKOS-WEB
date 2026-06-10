import { Award, Heart, Users, Zap } from 'lucide-react';

const features = [
  {
    icon: Award,
    title: 'Calidad Premium',
    description: 'Los mejores ingredientes seleccionados cuidadosamente'
  },
  {
    icon: Zap,
    title: 'Servicio Rápido',
    description: 'Tu pedido listo en tiempo récord sin comprometer el sabor'
  },
  {
    icon: Heart,
    title: 'Hecho con Amor',
    description: 'Cada platillo es preparado con dedicación y pasión'
  },
  {
    icon: Users,
    title: 'Para Toda la Familia',
    description: 'Opciones deliciosas para todos los gustos y edades'
  },
];

export default function About() {
  return (
    <section id="nosotros" className="py-20 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          {/* Content */}
          <div>
            <h2 className="text-4xl md:text-5xl font-bold text-[#111111] mb-6">
              Sobre Nosotros
            </h2>
            <p className="text-xl text-gray-600 mb-6">
              En <span className="font-bold text-[#D62828]">KENTAKITOS</span>, nos apasiona servir comida rápida de la más alta calidad.
              Desde 2020, hemos estado deleitando a nuestros clientes con sabores únicos e irresistibles.
            </p>
            <p className="text-lg text-gray-600 mb-8">
              Nuestra misión es simple: ofrecer la mejor experiencia culinaria con ingredientes frescos,
              recetas secretas y un servicio excepcional. Cada bocado cuenta una historia de dedicación y amor por la buena comida.
            </p>

            {/* Features Grid */}
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
              {features.map((feature, index) => (
                <div key={index} className="flex items-start space-x-3">
                  <div className="flex-shrink-0 w-10 h-10 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center">
                    <feature.icon className="w-5 h-5 text-white" />
                  </div>
                  <div>
                    <h4 className="font-bold text-[#111111] mb-1">{feature.title}</h4>
                    <p className="text-sm text-gray-600">{feature.description}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Image Placeholder */}
          <div className="relative">
            <div className="w-full h-[500px] bg-gradient-to-br from-[#D62828]/20 to-[#F77F00]/20 rounded-3xl border border-gray-200 flex items-center justify-center overflow-hidden">
              <div className="text-center">
                <div className="text-9xl mb-4">🍗</div>
                <p className="text-2xl font-bold text-[#111111]">KENTAKITOS</p>
                <p className="text-gray-600">Tradición y Sabor desde 2020</p>
              </div>
            </div>
            {/* Decorative Elements */}
            <div className="absolute -top-6 -right-6 w-32 h-32 bg-[#D62828] rounded-full blur-3xl opacity-20"></div>
            <div className="absolute -bottom-6 -left-6 w-32 h-32 bg-[#F77F00] rounded-full blur-3xl opacity-20"></div>
          </div>
        </div>
      </div>
    </section>
  );
}
