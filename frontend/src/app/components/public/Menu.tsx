import { Plus } from 'lucide-react';
import { useState } from 'react';

const categories = ['Todos', 'Hamburguesas', 'Pollo', 'Papas', 'Bebidas', 'Combos', 'Postres'];

const menuItems = [
  {
    id: 1,
    category: 'Hamburguesas',
    name: 'Kentucky Burger',
    price: 45.00,
    description: 'Doble carne, queso cheddar, lechuga, tomate y salsa especial',
    emoji: '🍔'
  },
  {
    id: 2,
    category: 'Hamburguesas',
    name: 'Mega Burger',
    price: 55.00,
    description: 'Triple carne, bacon, queso y cebolla caramelizada',
    emoji: '🍔'
  },
  {
    id: 3,
    category: 'Pollo',
    name: 'Pollo Crujiente 4 Piezas',
    price: 65.00,
    description: 'Pollo frito con nuestra receta secreta de especias',
    emoji: '🍗'
  },
  {
    id: 4,
    category: 'Pollo',
    name: 'Pollo Crujiente 8 Piezas',
    price: 120.00,
    description: 'Ideal para compartir en familia',
    emoji: '🍗'
  },
  {
    id: 5,
    category: 'Papas',
    name: 'Papas Clásicas',
    price: 25.00,
    description: 'Papas fritas crujientes con sal',
    emoji: '🍟'
  },
  {
    id: 6,
    category: 'Papas',
    name: 'Papas con Queso',
    price: 35.00,
    description: 'Papas fritas cubiertas con queso cheddar fundido',
    emoji: '🍟'
  },
  {
    id: 7,
    category: 'Bebidas',
    name: 'Refresco Mediano',
    price: 15.00,
    description: 'Coca-Cola, Sprite o Fanta',
    emoji: '🥤'
  },
  {
    id: 8,
    category: 'Bebidas',
    name: 'Refresco Grande',
    price: 20.00,
    description: 'Coca-Cola, Sprite o Fanta',
    emoji: '🥤'
  },
  {
    id: 9,
    category: 'Combos',
    name: 'Combo Familiar',
    price: 180.00,
    description: '8 piezas de pollo + papas grandes + 2 refrescos',
    emoji: '🎁'
  },
  {
    id: 10,
    category: 'Combos',
    name: 'Combo Personal',
    price: 85.00,
    description: 'Kentucky Burger + papas + refresco',
    emoji: '🎁'
  },
  {
    id: 11,
    category: 'Postres',
    name: 'Pay de Manzana',
    price: 30.00,
    description: 'Crujiente pay relleno de manzana',
    emoji: '🥧'
  },
  {
    id: 12,
    category: 'Postres',
    name: 'Helado Sundae',
    price: 35.00,
    description: 'Helado de vainilla con toppings a elegir',
    emoji: '🍨'
  },
];

export default function Menu() {
  const [selectedCategory, setSelectedCategory] = useState('Todos');

  const filteredItems = selectedCategory === 'Todos'
    ? menuItems
    : menuItems.filter(item => item.category === selectedCategory);

  return (
    <section id="menu" className="py-20 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Section Header */}
        <div className="text-center mb-12">
          <h2 className="text-4xl md:text-5xl font-bold text-[#111111] mb-4">
            Nuestro Menú
          </h2>
          <p className="text-xl text-gray-600 max-w-2xl mx-auto">
            Descubre nuestra deliciosa variedad de platillos preparados con los mejores ingredientes
          </p>
        </div>

        {/* Category Filter */}
        <div className="flex flex-wrap justify-center gap-3 mb-12">
          {categories.map((category) => (
            <button
              key={category}
              onClick={() => setSelectedCategory(category)}
              className={`px-6 py-2 rounded-full transition-all ${
                selectedCategory === category
                  ? 'bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white shadow-lg'
                  : 'bg-white text-[#111111] hover:bg-gray-100 border border-gray-200'
              }`}
            >
              {category}
            </button>
          ))}
        </div>

        {/* Menu Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredItems.map((item) => (
            <div
              key={item.id}
              className="bg-white rounded-2xl shadow-md hover:shadow-xl transition-all overflow-hidden group"
            >
              {/* Image Placeholder */}
              <div className="h-48 bg-gradient-to-br from-[#D62828]/10 to-[#F77F00]/10 flex items-center justify-center text-6xl group-hover:scale-105 transition-transform">
                {item.emoji}
              </div>

              {/* Content */}
              <div className="p-6">
                <div className="flex justify-between items-start mb-2">
                  <h3 className="text-xl font-bold text-[#111111]">{item.name}</h3>
                  <span className="text-2xl font-bold text-[#D62828]">
                    ${item.price.toFixed(2)}
                  </span>
                </div>
                <p className="text-gray-600 mb-4">{item.description}</p>
                <button className="w-full bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white py-3 rounded-lg hover:shadow-lg transition-all flex items-center justify-center space-x-2 group">
                  <Plus className="w-5 h-5 group-hover:rotate-90 transition-transform" />
                  <span>Agregar al Pedido</span>
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
