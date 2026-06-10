import { CreditCard, DollarSign, Receipt, Calendar } from 'lucide-react';

const sales = [
  { id: 1, date: '2026-05-26 14:35', customer: 'Juan Pérez', items: 3, total: 125.00, payment: 'Efectivo', status: 'Completado' },
  { id: 2, date: '2026-05-26 14:20', customer: 'María García', items: 2, total: 85.00, payment: 'Tarjeta', status: 'Completado' },
  { id: 3, date: '2026-05-26 14:15', customer: 'Carlos López', items: 5, total: 245.00, payment: 'Transferencia', status: 'Completado' },
  { id: 4, date: '2026-05-26 13:50', customer: 'Ana Martínez', items: 1, total: 45.00, payment: 'Efectivo', status: 'Completado' },
  { id: 5, date: '2026-05-26 13:30', customer: 'Pedro Sánchez', items: 4, total: 180.00, payment: 'Tarjeta', status: 'Completado' },
  { id: 6, date: '2026-05-26 13:10', customer: 'Laura Rodríguez', items: 2, total: 95.00, payment: 'Efectivo', status: 'Completado' },
  { id: 7, date: '2026-05-26 12:45', customer: 'Miguel Hernández', items: 3, total: 150.00, payment: 'Tarjeta', status: 'Completado' },
  { id: 8, date: '2026-05-26 12:20', customer: 'Sofia Torres', items: 6, total: 320.00, payment: 'Transferencia', status: 'Completado' },
];

const getPaymentIcon = (payment: string) => {
  switch (payment) {
    case 'Efectivo':
      return '💵';
    case 'Tarjeta':
      return '💳';
    case 'Transferencia':
      return '🏦';
    default:
      return '💰';
  }
};

export default function Sales() {
  const totalSales = sales.reduce((sum, sale) => sum + sale.total, 0);
  const avgTicket = totalSales / sales.length;

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div>
        <h1 className="text-3xl font-bold text-[#111111]">Ventas</h1>
        <p className="text-gray-600">Registro y gestión de ventas</p>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-start justify-between">
            <div>
              <p className="text-sm text-gray-600 mb-1">Ventas del Día</p>
              <p className="text-3xl font-bold text-[#111111]">${totalSales.toFixed(2)}</p>
              <p className="text-sm text-green-600 mt-1">+12.5% vs ayer</p>
            </div>
            <div className="w-12 h-12 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center">
              <DollarSign className="w-6 h-6 text-white" />
            </div>
          </div>
        </div>

        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-start justify-between">
            <div>
              <p className="text-sm text-gray-600 mb-1">Transacciones</p>
              <p className="text-3xl font-bold text-[#111111]">{sales.length}</p>
              <p className="text-sm text-blue-600 mt-1">Hoy</p>
            </div>
            <div className="w-12 h-12 bg-gradient-to-br from-[#F77F00] to-[#D62828] rounded-lg flex items-center justify-center">
              <Receipt className="w-6 h-6 text-white" />
            </div>
          </div>
        </div>

        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-start justify-between">
            <div>
              <p className="text-sm text-gray-600 mb-1">Ticket Promedio</p>
              <p className="text-3xl font-bold text-[#111111]">${avgTicket.toFixed(2)}</p>
              <p className="text-sm text-purple-600 mt-1">Por venta</p>
            </div>
            <div className="w-12 h-12 bg-gradient-to-br from-[#111111] to-[#F77F00] rounded-lg flex items-center justify-center">
              <CreditCard className="w-6 h-6 text-white" />
            </div>
          </div>
        </div>
      </div>

      {/* Filters */}
      <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
        <div className="flex flex-col sm:flex-row gap-4">
          <div className="flex items-center space-x-2 flex-1">
            <Calendar className="w-5 h-5 text-gray-400" />
            <input
              type="date"
              className="flex-1 px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
              defaultValue="2026-05-26"
            />
          </div>
          <select className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent">
            <option>Todos los métodos</option>
            <option>Efectivo</option>
            <option>Tarjeta</option>
            <option>Transferencia</option>
          </select>
          <button className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-2 rounded-lg hover:shadow-lg transition-all">
            Exportar
          </button>
        </div>
      </div>

      {/* Sales Table */}
      <div className="bg-white rounded-xl shadow-md border border-gray-100 overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  ID
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Fecha/Hora
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Cliente
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Items
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Total
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Método de Pago
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Estado
                </th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-100">
              {sales.map((sale) => (
                <tr key={sale.id} className="hover:bg-gray-50 transition-colors">
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className="font-mono text-sm text-gray-600">#{sale.id.toString().padStart(4, '0')}</span>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                    {sale.date}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className="font-medium text-[#111111]">{sale.customer}</span>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-gray-600">
                    {sale.items} productos
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className="font-bold text-[#D62828] text-lg">
                      ${sale.total.toFixed(2)}
                    </span>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className="inline-flex items-center space-x-2 px-3 py-1 text-xs font-semibold rounded-full bg-gray-100 text-gray-700">
                      <span>{getPaymentIcon(sale.payment)}</span>
                      <span>{sale.payment}</span>
                    </span>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className="px-3 py-1 text-xs font-semibold rounded-full bg-green-100 text-green-700">
                      {sale.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
