import { Check, X, Loader2 } from 'lucide-react';
import { useState, useEffect } from 'react';

interface MatrizPermisosDTO {
  modulos: string[];
  roles: string[];
  matriz: {
    [modulo: string]: {
      [rol: string]: boolean;
    };
  };
}

export default function Permissions() {
  const [data, setData] = useState<MatrizPermisosDTO | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    cargarMatriz();
  }, []);

  const cargarMatriz = async () => {
    try {
      setLoading(true);
      setError(null);

      const response = await fetch('https://shop.spring.informaticapp.com/api/admin/permissions');
      //const response = await fetch('http://localhost:2920/api/admin/permissions');

      if (!response.ok) {
        throw new Error('Error al cargar la matriz de permisos');
      }

      const result: MatrizPermisosDTO = await response.json();
      setData(result);
    } catch (err) {
      setError('No se pudieron cargar los permisos. Verifica que el backend esté corriendo.');
      console.error('Error:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleToggle = async (modulo: string, rol: string, currentValue: boolean) => {
    try {
      const response = await fetch('https://shop.spring.informaticapp.com/api/admin/permissions/toggle', {
        //const response = await fetch('http://localhost:2920/api/admin/permissions/toggle', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          moduloName: modulo,
          rolName: rol,
          conceder: !currentValue
        })
      });

      if (response.ok) {
        cargarMatriz();
      } else {
        alert('Error al actualizar el permiso');
      }
    } catch (err) {
      console.error(err);
      alert('Error de red al actualizar el permiso');
    }
  };

  if (loading && !data) {
    return (
      <div className="flex flex-col items-center justify-center h-[60vh]">
        <Loader2 className="w-12 h-12 text-[#F77F00] animate-spin mb-4" />
        <p className="text-gray-500 font-medium">Cargando matriz de permisos...</p>
      </div>
    );
  }

  if (error || !data) {
    return (
      <div className="bg-red-50 border border-red-200 rounded-xl p-8 text-center max-w-2xl mx-auto mt-12">
        <X className="w-16 h-16 text-red-500 mx-auto mb-4 opacity-50" />
        <p className="text-red-700 font-medium mb-4">{error}</p>
        <button
          onClick={cargarMatriz}
          className="bg-red-600 hover:bg-red-700 text-white px-6 py-2 rounded-lg font-medium transition-colors"
        >
          Reintentar
        </button>
      </div>
    );
  }

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
                <th className="px-6 py-4 text-left font-semibold">Módulo (Permiso)</th>
                {data.roles.map((role) => (
                  <th key={role} className="px-6 py-4 text-center font-semibold">
                    {role}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-100">
              {data.modulos.map((module, index) => (
                <tr key={module} className={`hover:bg-gray-50 transition-colors ${index % 2 === 0 ? 'bg-white' : 'bg-gray-50/50'}`}>
                  <td className="px-6 py-4 font-medium text-[#111111]">{module}</td>
                  {data.roles.map((role) => {
                    const hasPermission = data.matriz[module] && data.matriz[module][role];
                    return (
                      <td key={`${module}-${role}`} className="px-6 py-4 text-center">
                        <button
                          onClick={() => handleToggle(module, role, hasPermission)}
                          className="mx-auto cursor-pointer focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-[#F77F00] rounded-lg transition-transform hover:scale-110 active:scale-95"
                          title="Clic para cambiar el permiso"
                        >
                          {hasPermission ? (
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
                    );
                  })}
                </tr>
              ))}

              {data.modulos.length === 0 && (
                <tr>
                  <td colSpan={data.roles.length + 1} className="px-6 py-8 text-center text-gray-500">
                    No se encontraron permisos registrados en la base de datos.
                  </td>
                </tr>
              )}
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
              <p className="text-sm text-green-600">El rol tiene acceso a este permiso</p>
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
              <p className="text-sm text-red-600">El rol no tiene acceso a este permiso</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
