import { BarChart, Bar, LineChart, Line, PieChart, Pie, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { Download, TrendingUp, Calendar } from 'lucide-react';

const monthlyData = [
  { month: 'Ene', ventas: 45000, pedidos: 450, clientes: 1200 },
  { month: 'Feb', ventas: 52000, pedidos: 520, clientes: 1350 },
  { month: 'Mar', ventas: 48000, pedidos: 480, clientes: 1280 },
  { month: 'Abr', ventas: 61000, pedidos: 610, clientes: 1450 },
  { month: 'May', ventas: 73000, pedidos: 730, clientes: 1680 },
];

const categoryData = [
  { name: 'Hamburguesas', value: 35000, color: '#D62828' },
  { name: 'Pollo', value: 30000, color: '#F77F00' },
  { name: 'Combos', value: 25000, color: '#111111' },
  { name: 'Bebidas', value: 10000, color: '#ffa726' },
  { name: 'Postres', value: 8000, color: '#ef5350' },
];

const hourlyData = [
  { hour: '9am', ventas: 2500 },
  { hour: '10am', ventas: 3200 },
  { hour: '11am', ventas: 4100 },
  { hour: '12pm', ventas: 6500 },
  { hour: '1pm', ventas: 7800 },
  { hour: '2pm', ventas: 8200 },
  { hour: '3pm', ventas: 5500 },
  { hour: '4pm', ventas: 4200 },
  { hour: '5pm', ventas: 3800 },
  { hour: '6pm', ventas: 6100 },
  { hour: '7pm', ventas: 7500 },
  { hour: '8pm', ventas: 6800 },
];

export default function Reports() {
  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold text-[#111111]">Reportes</h1>
          <p className="text-gray-600">Análisis y estadísticas del negocio</p>
        </div>
        <button className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-3 rounded-lg hover:shadow-lg transition-all flex items-center space-x-2">
          <Download className="w-5 h-5" />
          <span>Exportar Reporte</span>
        </button>
      </div>

      {/* Filters */}
      <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
        <div className="flex flex-col sm:flex-row gap-4">
          <div className="flex items-center space-x-2">
            <Calendar className="w-5 h-5 text-gray-400" />
            <span className="text-sm text-gray-600">Desde:</span>
            <input
              type="date"
              className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
              defaultValue="2026-01-01"
            />
          </div>
          <div className="flex items-center space-x-2">
            <span className="text-sm text-gray-600">Hasta:</span>
            <input
              type="date"
              className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
              defaultValue="2026-05-26"
            />
          </div>
          <select className="px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent">
            <option>Todos los productos</option>
            <option>Hamburguesas</option>
            <option>Pollo</option>
            <option>Combos</option>
          </select>
        </div>
      </div>

      {/* Summary Cards */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-xl shadow-md p-6 text-white">
          <div className="flex items-start justify-between mb-4">
            <div>
              <p className="text-white/80 mb-1">Ventas Totales</p>
              <p className="text-4xl font-bold">$279,000</p>
            </div>
            <TrendingUp className="w-8 h-8" />
          </div>
          <p className="text-sm text-white/80">+23.5% vs mes anterior</p>
        </div>

        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <p className="text-gray-600 mb-1">Total de Pedidos</p>
          <p className="text-4xl font-bold text-[#111111] mb-2">2,790</p>
          <p className="text-sm text-green-600">+15.2% vs mes anterior</p>
        </div>

        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <p className="text-gray-600 mb-1">Clientes Atendidos</p>
          <p className="text-4xl font-bold text-[#111111] mb-2">6,960</p>
          <p className="text-sm text-blue-600">+18.7% vs mes anterior</p>
        </div>
      </div>

      {/* Charts Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Monthly Trend */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <h3 className="text-lg font-bold text-[#111111] mb-4">Tendencia Mensual</h3>
          <ResponsiveContainer width="100%" height={300}>
            <LineChart data={monthlyData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
              <XAxis dataKey="month" stroke="#666" />
              <YAxis stroke="#666" />
              <Tooltip />
              <Legend />
              <Line type="monotone" dataKey="ventas" stroke="#D62828" strokeWidth={3} name="Ventas ($)" />
              <Line type="monotone" dataKey="pedidos" stroke="#F77F00" strokeWidth={3} name="Pedidos" />
            </LineChart>
          </ResponsiveContainer>
        </div>

        {/* Sales by Category */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <h3 className="text-lg font-bold text-[#111111] mb-4">Ventas por Categoría</h3>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie
                data={categoryData}
                cx="50%"
                cy="50%"
                labelLine={false}
                label={(entry) => `${entry.name}: $${entry.value.toLocaleString()}`}
                outerRadius={100}
                fill="#8884d8"
                dataKey="value"
              >
                {categoryData.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={entry.color} />
                ))}
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
        </div>

        {/* Hourly Sales */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100 lg:col-span-2">
          <h3 className="text-lg font-bold text-[#111111] mb-4">Ventas por Hora</h3>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={hourlyData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
              <XAxis dataKey="hour" stroke="#666" />
              <YAxis stroke="#666" />
              <Tooltip />
              <Bar dataKey="ventas" fill="url(#colorGradient)" radius={[8, 8, 0, 0]} />
              <defs>
                <linearGradient id="colorGradient" x1="0" y1="0" x2="0" y2="1">
                  <stop offset="0%" stopColor="#D62828" />
                  <stop offset="100%" stopColor="#F77F00" />
                </linearGradient>
              </defs>
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>

      {/* Top Products */}
      <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
        <h3 className="text-lg font-bold text-[#111111] mb-4">Top 5 Productos del Mes</h3>
        <div className="space-y-4">
          {[
            { name: 'Combo Familiar', sales: 245, revenue: 44100 },
            { name: 'Kentucky Burger', sales: 432, revenue: 19440 },
            { name: 'Pollo Crujiente 8 Pzs', sales: 198, revenue: 23760 },
            { name: 'Mega Burger', sales: 356, revenue: 19580 },
            { name: 'Papas con Queso', sales: 567, revenue: 19845 },
          ].map((product, index) => (
            <div key={index} className="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
              <div className="flex items-center space-x-4">
                <div className={`w-10 h-10 rounded-full bg-gradient-to-br from-[#D62828] to-[#F77F00] flex items-center justify-center text-white font-bold`}>
                  {index + 1}
                </div>
                <div>
                  <p className="font-bold text-[#111111]">{product.name}</p>
                  <p className="text-sm text-gray-600">{product.sales} unidades vendidas</p>
                </div>
              </div>
              <div className="text-right">
                <p className="font-bold text-[#D62828] text-xl">${product.revenue.toLocaleString()}</p>
                <p className="text-sm text-gray-600">Ingresos totales</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
