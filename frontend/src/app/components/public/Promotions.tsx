import { useNavigate } from 'react-router-dom';
import { Clock, Tag, Sparkles } from 'lucide-react';

const promotions = [
  {
    id: 1,
    title: '2x1 en Hamburguesas',
    discount: '50% OFF',
    description: 'Compra una hamburguesa y llévate la segunda al 50% de descuento',
    validity: 'Válido hasta el 31 de Mayo',
    emoji: '🍔',
    color: 'from-[#D62828] to-[#F77F00]'
  },
  {
    id: 2,
    title: 'Combo Familiar Especial',
    discount: '$180',
    description: '8 piezas de pollo + papas grandes + 2 litros de refresco',
    validity: 'Todos los días',
    emoji: '🎁',
    color: 'from-[#F77F00] to-[#D62828]'
  },
  {
    id: 3,
    title: 'Happy Hour',
    discount: '30% OFF',
    description: 'De 3pm a 6pm - Descuento en toda la carta',
    validity: 'Lunes a Viernes',
    emoji: '🎉',
    color: 'from-[#D62828] to-[#F77F00]'
  },
  {
    id: 4,
    title: 'Martes de Pollo',
    discount: '20% OFF',
    description: 'Descuento especial en todos los platillos de pollo',
    validity: 'Todos los Martes',
    emoji: '🍗',
    color: 'from-[#F77F00] to-[#D62828]'
  },
];

export default function Promotions() {
  const navigate = useNavigate();
  return (
    <section id="promociones" className="py-20 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Section Header */}
        <div className="text-center mb-12">
          <div className="inline-flex items-center space-x-2 bg-gradient-to-r from-[#D62828]/10 to-[#F77F00]/10 px-4 py-2 rounded-full mb-4">
            <Sparkles className="w-4 h-4 text-[#D62828]" />
            <span className="text-sm font-semibold text-[#D62828]">OFERTAS ESPECIALES</span>
          </div>
          <h2 className="text-4xl md:text-5xl font-bold text-[#111111] mb-4">
            Promociones
          </h2>
          <p className="text-xl text-gray-600 max-w-2xl mx-auto">
            Aprovecha nuestras increíbles ofertas y ahorra en tus platillos favoritos
          </p>
        </div>

        {/* Promotions Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {promotions.map((promo) => (
            <div
              key={promo.id}
              className="relative bg-gradient-to-br from-gray-50 to-white rounded-2xl shadow-lg hover:shadow-2xl transition-all overflow-hidden border border-gray-100 group"
            >
              {/* Discount Badge */}
              <div className={`absolute top-4 right-4 bg-gradient-to-r ${promo.color} text-white px-4 py-2 rounded-full font-bold text-lg shadow-lg z-10`}>
                {promo.discount}
              </div>

              <div className="p-8">
                <div className="flex items-start space-x-4">
                  {/* Emoji */}
                  <div className="text-6xl flex-shrink-0 group-hover:scale-110 transition-transform">
                    {promo.emoji}
                  </div>

                  {/* Content */}
                  <div className="flex-1">
                    <h3 className="text-2xl font-bold text-[#111111] mb-2">
                      {promo.title}
                    </h3>
                    <p className="text-gray-600 mb-4">
                      {promo.description}
                    </p>
                    <div className="flex items-center space-x-2 text-sm text-gray-500">
                      <Clock className="w-4 h-4" />
                      <span>{promo.validity}</span>
                    </div>
                  </div>
                </div>

                {/* CTA Button */}
                <button 
                  onClick={() => navigate('/login')}
                  className={`w-full mt-6 bg-gradient-to-r ${promo.color} text-white py-3 rounded-lg hover:shadow-lg transition-all flex items-center justify-center space-x-2`}
                >
                  <Tag className="w-5 h-5" />
                  <span>Ordenar Ahora</span>
                </button>
              </div>

              {/* Decorative Element */}
              <div className="absolute bottom-0 right-0 w-32 h-32 bg-gradient-to-br from-[#D62828]/5 to-[#F77F00]/5 rounded-tl-full"></div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
