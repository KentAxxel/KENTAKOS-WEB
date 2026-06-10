import { Plus, Search, AlertTriangle, TrendingDown, TrendingUp, Package } from 'lucide-react';
import { useState } from 'react';

const inventory = [
  { id: 1, name: 'Carne de Res', quantity: 45, unit: 'kg', minStock: 30, category: 'Carnes', status: 'ok' },
  { id: 2, name: 'Pan para Hamburguesa', quantity: 120, unit: 'pzs', minStock: 100, category: 'Panadería', status: 'ok' },
  { id: 3, name: 'Pollo Entero', quantity: 15, unit: 'kg', minStock: 20, category: 'Carnes', status: 'low' },
  { id: 4, name: 'Papas', quantity: 80, unit: 'kg', minStock: 50, category: 'Verduras', status: 'ok' },
  { id: 5, name: 'Queso Cheddar', quantity: 8, unit: 'kg', minStock: 15, category: 'Lácteos', status: 'critical' },
  { id: 6, name: 'Lechuga', quantity: 25, unit: 'kg', minStock: 20, category: 'Verduras', status: 'ok' },
  { id: 7, name: 'Tomate', quantity: 30, unit: 'kg', minStock: 25, category: 'Verduras', status: 'ok' },
  { id: 8, name: 'Aceite Vegetal', quantity: 18, unit: 'L', minStock: 25, category: 'Condimentos', status: 'low' },
  { id: 9, name: 'Refresco Coca-Cola', quantity: 150, unit: 'L', minStock: 100, category: 'Bebidas', status: 'ok' },
  { id: 10, name: 'Salsa Especial', quantity: 5, unit: 'L', minStock: 10, category: 'Salsas', status: 'critical' },
];

const getStatusBadge = (item: typeof inventory[0]) => {
  if (item.quantity <= item.minStock * 0.5) {
    return {
      color: 'bg-red-100 text-red-700 border-red-200',
      icon: <AlertTriangle className="w-4 h-4" />,
      label: 'Crítico'
    };
  } else if (item.quantity <= item.minStock) {
    return {
      color: 'bg-yellow-100 text-yellow-700 border-yellow-200',
      icon: <TrendingDown className="w-4 h-4" />,
      label: 'Bajo'
    };
  } else {
    return {
      color: 'bg-green-100 text-green-700 border-green-200',
      icon: <TrendingUp className="w-4 h-4" />,
      label: 'Normal'
    };
  }
};

export default function Inventory() {
  const [searchTerm, setSearchTerm] = useState('');

  const stats = {
    total: inventory.length,
    critical: inventory.filter(i => i.quantity <= i.minStock * 0.5).length,
    low: inventory.filter(i => i.quantity > i.minStock * 0.5 && i.quantity <= i.minStock).length,
    ok: inventory.filter(i => i.quantity > i.minStock).length,
  };

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold text-[#111111]">Inventario</h1>
          <p className="text-gray-600">Control de insumos y stock</p>
        </div>
        <button className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-3 rounded-lg hover:shadow-lg transition-all flex items-center space-x-2">
          <Plus className="w-5 h-5" />
          <span>Registrar Entrada</span>
        </button>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-center space-x-3">
            <Package className="w-8 h-8 text-gray-700" />
            <div>
              <p className="text-sm text-gray-600 mb-1">Total Items</p>
              <p className="text-3xl font-bold text-[#111111]">{stats.total}</p>
            </div>
          </div>
        </div>
        <div className="bg-red-50 rounded-xl shadow-md p-6 border border-red-100">
          <div className="flex items-center space-x-3">
            <AlertTriangle className="w-8 h-8 text-red-700" />
            <div>
              <p className="text-sm text-red-700 mb-1">Críticos</p>
              <p className="text-3xl font-bold text-red-700">{stats.critical}</p>
            </div>
          </div>
        </div>
        <div className="bg-yellow-50 rounded-xl shadow-md p-6 border border-yellow-100">
          <div className="flex items-center space-x-3">
            <TrendingDown className="w-8 h-8 text-yellow-700" />
            <div>
              <p className="text-sm text-yellow-700 mb-1">Stock Bajo</p>
              <p className="text-3xl font-bold text-yellow-700">{stats.low}</p>
            </div>
          </div>
        </div>
        <div className="bg-green-50 rounded-xl shadow-md p-6 border border-green-100">
          <div className="flex items-center space-x-3">
            <TrendingUp className="w-8 h-8 text-green-700" />
            <div>
              <p className="text-sm text-green-700 mb-1">Normal</p>
              <p className="text-3xl font-bold text-green-700">{stats.ok}</p>
            </div>
          </div>
        </div>
      </div>

      {/* Filters */}
      <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
        <div className="flex flex-col sm:flex-row gap-4">
          <div className="flex-1 relative">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
            <input
              type="text"
              placeholder="Buscar insumos..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="w-full pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
            />
          </div>
          <select className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent">
            <option>Todas las categorías</option>
            <option>Carnes</option>
            <option>Verduras</option>
            <option>Lácteos</option>
            <option>Bebidas</option>
            <option>Condimentos</option>
            <option>Panadería</option>
          </select>
          <select className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent">
            <option>Todos los estados</option>
            <option>Crítico</option>
            <option>Bajo</option>
            <option>Normal</option>
          </select>
        </div>
      </div>

      {/* Inventory Table */}
      <div className="bg-white rounded-xl shadow-md border border-gray-100 overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Ingrediente
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Categoría
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Cantidad
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Stock Mínimo
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Estado
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Progreso
                </th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-100">
              {inventory.map((item) => {
                const statusBadge = getStatusBadge(item);
                const percentage = (item.quantity / (item.minStock * 2)) * 100;
                return (
                  <tr key={item.id} className="hover:bg-gray-50 transition-colors">
                    <td className="px-6 py-4 whitespace-nowrap">
                      <span className="font-medium text-[#111111]">{item.name}</span>
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      <span className="px-3 py-1 text-xs font-semibold rounded-full bg-gray-100 text-gray-700">
                        {item.category}
                      </span>
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      <span className="font-bold text-[#111111]">
                        {item.quantity} {item.unit}
                      </span>
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-gray-600">
                      {item.minStock} {item.unit}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      <span className={`px-3 py-1 text-xs font-semibold rounded-full border inline-flex items-center space-x-1 ${statusBadge.color}`}>
                        {statusBadge.icon}
                        <span>{statusBadge.label}</span>
                      </span>
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      <div className="w-full bg-gray-200 rounded-full h-2">
                        <div
                          className={`h-2 rounded-full ${
                            percentage >= 100 ? 'bg-green-500' :
                            percentage >= 50 ? 'bg-yellow-500' : 'bg-red-500'
                          }`}
                          style={{ width: `${Math.min(percentage, 100)}%` }}
                        ></div>
                      </div>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
