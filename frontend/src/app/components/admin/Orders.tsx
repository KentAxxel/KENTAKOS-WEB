import { Clock, CheckCircle, Package, Truck } from 'lucide-react';

const orders = [
  { id: 1, customer: 'Juan Pérez', items: 3, total: 125.00, status: 'pendiente', time: '5 min', table: 'Mesa 3' },
  { id: 2, customer: 'María García', items: 2, total: 85.00, status: 'preparando', time: '12 min', table: 'Mesa 7' },
  { id: 3, customer: 'Carlos López', items: 5, total: 245.00, status: 'listo', time: '18 min', table: 'Mesa 2' },
  { id: 4, customer: 'Ana Martínez', items: 1, total: 45.00, status: 'entregado', time: '25 min', table: 'Para llevar' },
  { id: 5, customer: 'Pedro Sánchez', items: 4, total: 180.00, status: 'preparando', time: '8 min', table: 'Mesa 5' },
  { id: 6, customer: 'Laura Rodríguez', items: 2, total: 95.00, status: 'pendiente', time: '3 min', table: 'Mesa 8' },
];

const getStatusColor = (status: string) => {
  switch (status) {
    case 'pendiente':
      return 'bg-yellow-50 border-yellow-200 text-yellow-700';
    case 'preparando':
      return 'bg-blue-50 border-blue-200 text-blue-700';
    case 'listo':
      return 'bg-green-50 border-green-200 text-green-700';
    case 'entregado':
      return 'bg-gray-50 border-gray-200 text-gray-700';
    default:
      return 'bg-gray-50 border-gray-200 text-gray-700';
  }
};

const getStatusIcon = (status: string) => {
  switch (status) {
    case 'pendiente':
      return <Clock className="w-5 h-5" />;
    case 'preparando':
      return <Package className="w-5 h-5" />;
    case 'listo':
      return <CheckCircle className="w-5 h-5" />;
    case 'entregado':
      return <Truck className="w-5 h-5" />;
    default:
      return null;
  }
};

export default function Orders() {
  const stats = {
    pendiente: orders.filter(o => o.status === 'pendiente').length,
    preparando: orders.filter(o => o.status === 'preparando').length,
    listo: orders.filter(o => o.status === 'listo').length,
    entregado: orders.filter(o => o.status === 'entregado').length,
  };

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div>
        <h1 className="text-3xl font-bold text-[#111111]">Pedidos</h1>
        <p className="text-gray-600">Seguimiento de pedidos en tiempo real</p>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
        <div className="bg-yellow-50 rounded-xl shadow-md p-6 border border-yellow-100">
          <div className="flex items-center space-x-3">
            <Clock className="w-8 h-8 text-yellow-700" />
            <div>
              <p className="text-sm text-yellow-700 mb-1">Pendientes</p>
              <p className="text-3xl font-bold text-yellow-700">{stats.pendiente}</p>
            </div>
          </div>
        </div>
        <div className="bg-blue-50 rounded-xl shadow-md p-6 border border-blue-100">
          <div className="flex items-center space-x-3">
            <Package className="w-8 h-8 text-blue-700" />
            <div>
              <p className="text-sm text-blue-700 mb-1">Preparando</p>
              <p className="text-3xl font-bold text-blue-700">{stats.preparando}</p>
            </div>
          </div>
        </div>
        <div className="bg-green-50 rounded-xl shadow-md p-6 border border-green-100">
          <div className="flex items-center space-x-3">
            <CheckCircle className="w-8 h-8 text-green-700" />
            <div>
              <p className="text-sm text-green-700 mb-1">Listos</p>
              <p className="text-3xl font-bold text-green-700">{stats.listo}</p>
            </div>
          </div>
        </div>
        <div className="bg-gray-50 rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-center space-x-3">
            <Truck className="w-8 h-8 text-gray-700" />
            <div>
              <p className="text-sm text-gray-700 mb-1">Entregados</p>
              <p className="text-3xl font-bold text-gray-700">{stats.entregado}</p>
            </div>
          </div>
        </div>
      </div>

      {/* Orders Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {orders.map((order) => (
          <div
            key={order.id}
            className={`rounded-xl shadow-md p-6 border-2 transition-all hover:shadow-xl ${getStatusColor(order.status)}`}
          >
            <div className="flex items-start justify-between mb-4">
              <div>
                <h3 className="font-bold text-lg">Pedido #{order.id}</h3>
                <p className="text-sm opacity-75">{order.customer}</p>
              </div>
              <div className="flex items-center space-x-2">
                {getStatusIcon(order.status)}
              </div>
            </div>

            <div className="space-y-2 mb-4">
              <div className="flex justify-between text-sm">
                <span className="opacity-75">Ubicación:</span>
                <span className="font-semibold">{order.table}</span>
              </div>
              <div className="flex justify-between text-sm">
                <span className="opacity-75">Items:</span>
                <span className="font-semibold">{order.items} productos</span>
              </div>
              <div className="flex justify-between text-sm">
                <span className="opacity-75">Total:</span>
                <span className="font-semibold">${order.total.toFixed(2)}</span>
              </div>
              <div className="flex justify-between text-sm">
                <span className="opacity-75">Tiempo:</span>
                <span className="font-semibold">{order.time}</span>
              </div>
            </div>

            <div className="pt-4 border-t border-current/20">
              <div className="flex items-center justify-between">
                <span className="text-sm font-bold uppercase">{order.status}</span>
                {order.status !== 'entregado' && (
                  <button className="px-4 py-2 bg-white rounded-lg text-sm font-semibold hover:shadow-md transition-all">
                    Actualizar
                  </button>
                )}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
