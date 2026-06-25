import { TrendingUp, DollarSign, ShoppingBag, Users, ArrowUp, ArrowDown, Shield } from 'lucide-react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, LineChart, Line, PieChart, Pie, Cell } from 'recharts';

const salesData = [
  { name: 'Lun', ventas: 4500 },
  { name: 'Mar', ventas: 5200 },
  { name: 'Mie', ventas: 4800 },
  { name: 'Jue', ventas: 6100 },
  { name: 'Vie', ventas: 7300 },
  { name: 'Sab', ventas: 8500 },
  { name: 'Dom', ventas: 7800 },
];

const productData = [
  { name: 'Hamburguesas', value: 35 },
  { name: 'Pollo', value: 30 },
  { name: 'Combos', value: 20 },
  { name: 'Bebidas', value: 10 },
  { name: 'Postres', value: 5 },
];

const COLORS = ['#D62828', '#F77F00', '#111111', '#ffa726', '#ef5350'];

const stats = [
  {
    title: 'Ventas del Día',
    value: '$8,542',
    change: '+12.5%',
    isPositive: true,
    icon: DollarSign,
    color: 'from-[#D62828] to-[#F77F00]'
  },
  {
    title: 'Pedidos Activos',
    value: '23',
    change: '+5',
    isPositive: true,
    icon: ShoppingBag,
    color: 'from-[#F77F00] to-[#D62828]'
  },
  {
    title: 'Ventas Mensuales',
    value: '$156,420',
    change: '+23.1%',
    isPositive: true,
    icon: TrendingUp,
    color: 'from-[#D62828] to-[#111111]'
  },
  {
    title: 'Clientes Hoy',
    value: '342',
    change: '-2.4%',
    isPositive: false,
    icon: Users,
    color: 'from-[#111111] to-[#F77F00]'
  },
];

const topProducts = [
  { name: 'Kentucky Burger', sales: 156, revenue: '$7,020' },
  { name: 'Pollo Crujiente 8 Pzs', sales: 89, revenue: '$10,680' },
  { name: 'Combo Familiar', sales: 67, revenue: '$12,060' },
  { name: 'Papas con Queso', sales: 234, revenue: '$8,190' },
  { name: 'Mega Burger', sales: 98, revenue: '$5,390' },
];

export default function Dashboard() {
  const userString = localStorage.getItem('user');
  const user = userString ? JSON.parse(userString) : null;

  if (!user?.role || user.role === 'Sin Rol') {
    return (
      <div className="flex flex-col items-center justify-center min-h-[60vh] text-center space-y-6">
        <div className="w-24 h-24 bg-red-100 text-red-600 rounded-full flex items-center justify-center">
          <Shield className="w-12 h-12" />
        </div>
        <h2 className="text-3xl font-bold text-[#111111]">Acceso Restringido</h2>
        <p className="text-gray-600 max-w-md text-lg">
          Actualmente no tienes un rol ni permisos asignados en el sistema. 
          Por favor, solicita a un Administrador que configure tu cuenta.
        </p>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div>
        <h1 className="text-3xl font-bold text-[#111111]">Dashboard</h1>
        <p className="text-gray-600">Vista general del negocio</p>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat, index) => (
          <div key={index} className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
            <div className="flex items-start justify-between mb-4">
              <div className={`w-12 h-12 bg-gradient-to-br ${stat.color} rounded-lg flex items-center justify-center`}>
                <stat.icon className="w-6 h-6 text-white" />
              </div>
              <div className={`flex items-center space-x-1 text-sm ${stat.isPositive ? 'text-green-600' : 'text-red-600'}`}>
                {stat.isPositive ? <ArrowUp className="w-4 h-4" /> : <ArrowDown className="w-4 h-4" />}
                <span>{stat.change}</span>
              </div>
            </div>
            <h3 className="text-2xl font-bold text-[#111111] mb-1">{stat.value}</h3>
            <p className="text-sm text-gray-600">{stat.title}</p>
          </div>
        ))}
      </div>

      {/* Charts Row */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Sales Chart */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <h3 className="text-lg font-bold text-[#111111] mb-4">Ventas Semanales</h3>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={salesData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
              <XAxis dataKey="name" stroke="#666" />
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

        {/* Product Distribution */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <h3 className="text-lg font-bold text-[#111111] mb-4">Distribución de Productos</h3>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie
                data={productData}
                cx="50%"
                cy="50%"
                labelLine={false}
                label={(entry) => `${entry.name}: ${entry.value}%`}
                outerRadius={100}
                fill="#8884d8"
                dataKey="value"
              >
                {productData.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
        </div>
      </div>

      {/* Top Products Table */}
      <div className="bg-white rounded-xl shadow-md border border-gray-100 overflow-hidden">
        <div className="p-6 border-b border-gray-100">
          <h3 className="text-lg font-bold text-[#111111]">Productos Más Vendidos</h3>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Producto
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Ventas
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Ingresos
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                  Progreso
                </th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-100">
              {topProducts.map((product, index) => (
                <tr key={index} className="hover:bg-gray-50 transition-colors">
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="flex items-center">
                      <div className="w-2 h-2 bg-gradient-to-r from-[#D62828] to-[#F77F00] rounded-full mr-3"></div>
                      <span className="font-medium text-[#111111]">{product.name}</span>
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-gray-600">
                    {product.sales} unidades
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap font-semibold text-[#111111]">
                    {product.revenue}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="w-full bg-gray-200 rounded-full h-2">
                      <div
                        className="bg-gradient-to-r from-[#D62828] to-[#F77F00] h-2 rounded-full"
                        style={{ width: `${(product.sales / 250) * 100}%` }}
                      ></div>
                    </div>
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
