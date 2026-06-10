import { Plus, Users, Clock, CheckCircle } from 'lucide-react';

const tables = [
  { id: 1, number: 1, capacity: 4, status: 'libre', time: null },
  { id: 2, number: 2, capacity: 2, status: 'ocupada', time: '45 min' },
  { id: 3, number: 3, capacity: 6, status: 'ocupada', time: '1h 20min' },
  { id: 4, number: 4, capacity: 4, status: 'libre', time: null },
  { id: 5, number: 5, capacity: 2, status: 'reservada', time: '18:00' },
  { id: 6, number: 6, capacity: 8, status: 'ocupada', time: '35 min' },
  { id: 7, number: 7, capacity: 4, status: 'libre', time: null },
  { id: 8, number: 8, capacity: 2, status: 'libre', time: null },
  { id: 9, number: 9, capacity: 6, status: 'reservada', time: '19:30' },
  { id: 10, number: 10, capacity: 4, status: 'ocupada', time: '25 min' },
  { id: 11, number: 11, capacity: 2, status: 'libre', time: null },
  { id: 12, number: 12, capacity: 4, status: 'libre', time: null },
];

const getStatusColor = (status: string) => {
  switch (status) {
    case 'libre':
      return 'bg-green-50 border-green-200 text-green-700';
    case 'ocupada':
      return 'bg-red-50 border-red-200 text-red-700';
    case 'reservada':
      return 'bg-yellow-50 border-yellow-200 text-yellow-700';
    default:
      return 'bg-gray-50 border-gray-200 text-gray-700';
  }
};

const getStatusIcon = (status: string) => {
  switch (status) {
    case 'libre':
      return <CheckCircle className="w-8 h-8" />;
    case 'ocupada':
      return <Users className="w-8 h-8" />;
    case 'reservada':
      return <Clock className="w-8 h-8" />;
    default:
      return null;
  }
};

export default function Tables() {
  const stats = {
    total: tables.length,
    libre: tables.filter(t => t.status === 'libre').length,
    ocupada: tables.filter(t => t.status === 'ocupada').length,
    reservada: tables.filter(t => t.status === 'reservada').length,
  };

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold text-[#111111]">Mesas</h1>
          <p className="text-gray-600">Gestión y seguimiento de mesas</p>
        </div>
        <button className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-3 rounded-lg hover:shadow-lg transition-all flex items-center space-x-2">
          <Plus className="w-5 h-5" />
          <span>Nueva Mesa</span>
        </button>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <p className="text-sm text-gray-600 mb-1">Total Mesas</p>
          <p className="text-3xl font-bold text-[#111111]">{stats.total}</p>
        </div>
        <div className="bg-green-50 rounded-xl shadow-md p-6 border border-green-100">
          <p className="text-sm text-green-700 mb-1">Libres</p>
          <p className="text-3xl font-bold text-green-700">{stats.libre}</p>
        </div>
        <div className="bg-red-50 rounded-xl shadow-md p-6 border border-red-100">
          <p className="text-sm text-red-700 mb-1">Ocupadas</p>
          <p className="text-3xl font-bold text-red-700">{stats.ocupada}</p>
        </div>
        <div className="bg-yellow-50 rounded-xl shadow-md p-6 border border-yellow-100">
          <p className="text-sm text-yellow-700 mb-1">Reservadas</p>
          <p className="text-3xl font-bold text-yellow-700">{stats.reservada}</p>
        </div>
      </div>

      {/* Tables Grid */}
      <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {tables.map((table) => (
          <div
            key={table.id}
            className={`rounded-xl shadow-md p-6 border-2 transition-all cursor-pointer hover:shadow-xl ${getStatusColor(table.status)}`}
          >
            <div className="flex flex-col items-center text-center space-y-3">
              {getStatusIcon(table.status)}
              <div>
                <h3 className="text-2xl font-bold">Mesa {table.number}</h3>
                <p className="text-sm opacity-75 flex items-center justify-center space-x-1 mt-1">
                  <Users className="w-4 h-4" />
                  <span>{table.capacity} personas</span>
                </p>
              </div>
              <div className="w-full pt-3 border-t border-current/20">
                <p className="text-sm font-semibold uppercase">{table.status}</p>
                {table.time && (
                  <p className="text-xs opacity-75 mt-1">{table.time}</p>
                )}
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Legend */}
      <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
        <h3 className="font-bold text-[#111111] mb-4">Leyenda</h3>
        <div className="flex flex-wrap gap-4">
          <div className="flex items-center space-x-2">
            <div className="w-4 h-4 bg-green-200 rounded"></div>
            <span className="text-sm text-gray-600">Libre - Mesa disponible</span>
          </div>
          <div className="flex items-center space-x-2">
            <div className="w-4 h-4 bg-red-200 rounded"></div>
            <span className="text-sm text-gray-600">Ocupada - Mesa en uso</span>
          </div>
          <div className="flex items-center space-x-2">
            <div className="w-4 h-4 bg-yellow-200 rounded"></div>
            <span className="text-sm text-gray-600">Reservada - Mesa reservada</span>
          </div>
        </div>
      </div>
    </div>
  );
}
