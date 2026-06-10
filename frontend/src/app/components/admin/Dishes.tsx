import { Plus, Search, Edit, Trash2, Eye, EyeOff } from 'lucide-react';
import { useState } from 'react';

const dishes = [
  { id: 1, name: 'Kentucky Burger', category: 'Hamburguesas', price: 45.00, stock: 50, status: 'activo', emoji: '🍔' },
  { id: 2, name: 'Mega Burger', category: 'Hamburguesas', price: 55.00, stock: 35, status: 'activo', emoji: '🍔' },
  { id: 3, name: 'Pollo Crujiente 4 Piezas', category: 'Pollo', price: 65.00, stock: 80, status: 'activo', emoji: '🍗' },
  { id: 4, name: 'Pollo Crujiente 8 Piezas', category: 'Pollo', price: 120.00, stock: 45, status: 'activo', emoji: '🍗' },
  { id: 5, name: 'Papas Clásicas', category: 'Papas', price: 25.00, stock: 120, status: 'activo', emoji: '🍟' },
  { id: 6, name: 'Papas con Queso', category: 'Papas', price: 35.00, stock: 90, status: 'activo', emoji: '🍟' },
  { id: 7, name: 'Refresco Mediano', category: 'Bebidas', price: 15.00, stock: 200, status: 'activo', emoji: '🥤' },
  { id: 8, name: 'Combo Familiar', category: 'Combos', price: 180.00, stock: 25, status: 'activo', emoji: '🎁' },
  { id: 9, name: 'Pay de Manzana', category: 'Postres', price: 30.00, stock: 40, status: 'inactivo', emoji: '🥧' },
];

export default function Dishes() {
  const [searchTerm, setSearchTerm] = useState('');

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold text-[#111111]">Platillos</h1>
          <p className="text-gray-600">Gestión del menú y productos</p>
        </div>
        <button className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-3 rounded-lg hover:shadow-lg transition-all flex items-center space-x-2">
          <Plus className="w-5 h-5" />
          <span>Nuevo Platillo</span>
        </button>
      </div>

      {/* Filters */}
      <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
        <div className="flex flex-col sm:flex-row gap-4">
          <div className="flex-1 relative">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
            <input
              type="text"
              placeholder="Buscar platillos..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="w-full pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
            />
          </div>
          <select className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent">
            <option>Todas las categorías</option>
            <option>Hamburguesas</option>
            <option>Pollo</option>
            <option>Papas</option>
            <option>Bebidas</option>
            <option>Combos</option>
            <option>Postres</option>
          </select>
          <select className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent">
            <option>Todos los estados</option>
            <option>Activo</option>
            <option>Inactivo</option>
          </select>
        </div>
      </div>

      {/* Dishes Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {dishes.map((dish) => (
          <div key={dish.id} className="bg-white rounded-xl shadow-md border border-gray-100 overflow-hidden hover:shadow-xl transition-all">
            {/* Image */}
            <div className="h-40 bg-gradient-to-br from-[#D62828]/10 to-[#F77F00]/10 flex items-center justify-center text-6xl">
              {dish.emoji}
            </div>

            {/* Content */}
            <div className="p-6">
              <div className="flex justify-between items-start mb-2">
                <div>
                  <h3 className="font-bold text-[#111111] text-lg mb-1">{dish.name}</h3>
                  <span className="px-2 py-1 text-xs font-semibold rounded-full bg-gray-100 text-gray-700">
                    {dish.category}
                  </span>
                </div>
                <span className="text-2xl font-bold text-[#D62828]">
                  ${dish.price.toFixed(2)}
                </span>
              </div>

              <div className="mt-4 flex items-center justify-between">
                <div>
                  <p className="text-sm text-gray-600">Stock disponible</p>
                  <p className="text-lg font-bold text-[#111111]">{dish.stock} unidades</p>
                </div>
                <span className={`px-3 py-1 text-xs font-semibold rounded-full inline-flex items-center space-x-1 ${
                  dish.status === 'activo'
                    ? 'bg-green-100 text-green-700'
                    : 'bg-red-100 text-red-700'
                }`}>
                  {dish.status === 'activo' ? (
                    <Eye className="w-3 h-3" />
                  ) : (
                    <EyeOff className="w-3 h-3" />
                  )}
                  <span className="capitalize">{dish.status}</span>
                </span>
              </div>

              {/* Actions */}
              <div className="mt-4 pt-4 border-t border-gray-100 flex gap-2">
                <button className="flex-1 flex items-center justify-center space-x-2 px-4 py-2 bg-gray-100 hover:bg-gray-200 rounded-lg transition-colors">
                  <Edit className="w-4 h-4" />
                  <span className="text-sm">Editar</span>
                </button>
                <button className="flex-1 flex items-center justify-center space-x-2 px-4 py-2 bg-red-50 hover:bg-red-100 text-[#D62828] rounded-lg transition-colors">
                  <Trash2 className="w-4 h-4" />
                  <span className="text-sm">Eliminar</span>
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
