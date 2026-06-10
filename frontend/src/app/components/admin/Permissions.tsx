import { Check, X } from 'lucide-react';

const modules = [
  'Dashboard',
  'Usuarios',
  'Roles',
  'Permisos',
  'Mesas',
  'Platillos',
  'Inventario',
  'Ventas',
  'Reportes',
  'Pedidos',
  'Configuración',
];

const roles = ['Administrador', 'Gerente', 'Cajero', 'Cocina', 'Mesero'];

const permissions = {
  Dashboard: { Administrador: true, Gerente: true, Cajero: true, Cocina: true, Mesero: true },
  Usuarios: { Administrador: true, Gerente: true, Cajero: false, Cocina: false, Mesero: false },
  Roles: { Administrador: true, Gerente: false, Cajero: false, Cocina: false, Mesero: false },
  Permisos: { Administrador: true, Gerente: false, Cajero: false, Cocina: false, Mesero: false },
  Mesas: { Administrador: true, Gerente: true, Cajero: true, Cocina: false, Mesero: true },
  Platillos: { Administrador: true, Gerente: true, Cajero: true, Cocina: true, Mesero: true },
  Inventario: { Administrador: true, Gerente: true, Cajero: false, Cocina: true, Mesero: false },
  Ventas: { Administrador: true, Gerente: true, Cajero: true, Cocina: false, Mesero: false },
  Reportes: { Administrador: true, Gerente: true, Cajero: false, Cocina: false, Mesero: false },
  Pedidos: { Administrador: true, Gerente: true, Cajero: true, Cocina: true, Mesero: true },
  Configuración: { Administrador: true, Gerente: false, Cajero: false, Cocina: false, Mesero: false },
};

export default function Permissions() {
  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div>
        <h1 className="text-3xl font-bold text-[#111111]">Permisos</h1>
        <p className="text-gray-600">Matriz de permisos por rol</p>
      </div>

      {/* Permissions Matrix */}
      <div className="bg-white rounded-xl shadow-md border border-gray-100 overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white">
              <tr>
                <th className="px-6 py-4 text-left font-semibold">Módulo</th>
                {roles.map((role) => (
                  <th key={role} className="px-6 py-4 text-center font-semibold">
                    {role}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-100">
              {modules.map((module, index) => (
                <tr key={module} className={`hover:bg-gray-50 transition-colors ${index % 2 === 0 ? 'bg-white' : 'bg-gray-50/50'}`}>
                  <td className="px-6 py-4 font-medium text-[#111111]">{module}</td>
                  {roles.map((role) => (
                    <td key={`${module}-${role}`} className="px-6 py-4 text-center">
                      <button className="mx-auto">
                        {permissions[module as keyof typeof permissions][role] ? (
                          <div className="w-8 h-8 bg-green-100 rounded-lg flex items-center justify-center hover:bg-green-200 transition-colors">
                            <Check className="w-5 h-5 text-green-600" />
                          </div>
                        ) : (
                          <div className="w-8 h-8 bg-red-100 rounded-lg flex items-center justify-center hover:bg-red-200 transition-colors">
                            <X className="w-5 h-5 text-red-600" />
                          </div>
                        )}
                      </button>
                    </td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Legend */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div className="bg-green-50 rounded-xl p-6 border border-green-100">
          <div className="flex items-center space-x-3">
            <div className="w-10 h-10 bg-green-100 rounded-lg flex items-center justify-center">
              <Check className="w-6 h-6 text-green-600" />
            </div>
            <div>
              <h4 className="font-bold text-green-700">Permiso Concedido</h4>
              <p className="text-sm text-green-600">El rol tiene acceso a este módulo</p>
            </div>
          </div>
        </div>
        <div className="bg-red-50 rounded-xl p-6 border border-red-100">
          <div className="flex items-center space-x-3">
            <div className="w-10 h-10 bg-red-100 rounded-lg flex items-center justify-center">
              <X className="w-6 h-6 text-red-600" />
            </div>
            <div>
              <h4 className="font-bold text-red-700">Permiso Denegado</h4>
              <p className="text-sm text-red-600">El rol no tiene acceso a este módulo</p>
            </div>
          </div>
        </div>
      </div>

      {/* Save Button */}
      <div className="flex justify-end">
        <button className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-8 py-3 rounded-lg hover:shadow-lg transition-all">
          Guardar Cambios
        </button>
      </div>
    </div>
  );
}
