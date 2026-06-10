import { Building, Globe, Palette, Bell, Lock, Database } from 'lucide-react';

export default function Settings() {
  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div>
        <h1 className="text-3xl font-bold text-[#111111]">Configuración</h1>
        <p className="text-gray-600">Ajustes generales del sistema</p>
      </div>

      {/* Settings Sections */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Restaurant Info */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-center space-x-3 mb-4">
            <div className="w-10 h-10 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center">
              <Building className="w-5 h-5 text-white" />
            </div>
            <h3 className="text-lg font-bold text-[#111111]">Información del Restaurante</h3>
          </div>
          <div className="space-y-4">
            <div>
              <label className="block text-sm text-gray-600 mb-1">Nombre</label>
              <input
                type="text"
                defaultValue="KENTAKITOS"
                className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
              />
            </div>
            <div>
              <label className="block text-sm text-gray-600 mb-1">Dirección</label>
              <input
                type="text"
                defaultValue="Av. Principal #123, Centro, Ciudad"
                className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
              />
            </div>
            <div>
              <label className="block text-sm text-gray-600 mb-1">Teléfono</label>
              <input
                type="text"
                defaultValue="+52 (555) 123-4567"
                className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
              />
            </div>
            <div>
              <label className="block text-sm text-gray-600 mb-1">Email</label>
              <input
                type="email"
                defaultValue="hola@kentakitos.com"
                className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#F77F00] focus:border-transparent"
              />
            </div>
          </div>
        </div>

        {/* Theme */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-center space-x-3 mb-4">
            <div className="w-10 h-10 bg-gradient-to-br from-[#F77F00] to-[#D62828] rounded-lg flex items-center justify-center">
              <Palette className="w-5 h-5 text-white" />
            </div>
            <h3 className="text-lg font-bold text-[#111111]">Tema y Apariencia</h3>
          </div>
          <div className="space-y-4">
            <div>
              <label className="block text-sm text-gray-600 mb-2">Colores del Sistema</label>
              <div className="grid grid-cols-3 gap-3">
                <div className="text-center">
                  <div className="w-full h-20 bg-[#111111] rounded-lg mb-2"></div>
                  <p className="text-xs text-gray-600">Negro</p>
                  <p className="text-xs font-mono text-gray-500">#111111</p>
                </div>
                <div className="text-center">
                  <div className="w-full h-20 bg-[#D62828] rounded-lg mb-2"></div>
                  <p className="text-xs text-gray-600">Rojo</p>
                  <p className="text-xs font-mono text-gray-500">#D62828</p>
                </div>
                <div className="text-center">
                  <div className="w-full h-20 bg-[#F77F00] rounded-lg mb-2"></div>
                  <p className="text-xs text-gray-600">Naranja</p>
                  <p className="text-xs font-mono text-gray-500">#F77F00</p>
                </div>
              </div>
            </div>
            <div>
              <label className="block text-sm text-gray-600 mb-2">Logo del Restaurante</label>
              <div className="border-2 border-dashed border-gray-300 rounded-lg p-8 text-center hover:border-[#F77F00] transition-colors cursor-pointer">
                <div className="w-16 h-16 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center mx-auto mb-3">
                  <span className="text-white font-bold text-2xl">K</span>
                </div>
                <p className="text-sm text-gray-600">Click para subir logo</p>
              </div>
            </div>
          </div>
        </div>

        {/* Notifications */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-center space-x-3 mb-4">
            <div className="w-10 h-10 bg-gradient-to-br from-[#111111] to-[#F77F00] rounded-lg flex items-center justify-center">
              <Bell className="w-5 h-5 text-white" />
            </div>
            <h3 className="text-lg font-bold text-[#111111]">Notificaciones</h3>
          </div>
          <div className="space-y-4">
            <div className="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
              <div>
                <p className="font-semibold text-[#111111]">Nuevos Pedidos</p>
                <p className="text-sm text-gray-600">Alertas de pedidos entrantes</p>
              </div>
              <label className="relative inline-flex items-center cursor-pointer">
                <input type="checkbox" className="sr-only peer" defaultChecked />
                <div className="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-2 peer-focus:ring-[#F77F00] rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-gradient-to-r peer-checked:from-[#D62828] peer-checked:to-[#F77F00]"></div>
              </label>
            </div>
            <div className="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
              <div>
                <p className="font-semibold text-[#111111]">Stock Bajo</p>
                <p className="text-sm text-gray-600">Alertas de inventario bajo</p>
              </div>
              <label className="relative inline-flex items-center cursor-pointer">
                <input type="checkbox" className="sr-only peer" defaultChecked />
                <div className="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-2 peer-focus:ring-[#F77F00] rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-gradient-to-r peer-checked:from-[#D62828] peer-checked:to-[#F77F00]"></div>
              </label>
            </div>
            <div className="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
              <div>
                <p className="font-semibold text-[#111111]">Ventas Diarias</p>
                <p className="text-sm text-gray-600">Resumen de ventas del día</p>
              </div>
              <label className="relative inline-flex items-center cursor-pointer">
                <input type="checkbox" className="sr-only peer" />
                <div className="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-2 peer-focus:ring-[#F77F00] rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-gradient-to-r peer-checked:from-[#D62828] peer-checked:to-[#F77F00]"></div>
              </label>
            </div>
          </div>
        </div>

        {/* Security */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100">
          <div className="flex items-center space-x-3 mb-4">
            <div className="w-10 h-10 bg-gradient-to-br from-[#D62828] to-[#111111] rounded-lg flex items-center justify-center">
              <Lock className="w-5 h-5 text-white" />
            </div>
            <h3 className="text-lg font-bold text-[#111111]">Seguridad</h3>
          </div>
          <div className="space-y-4">
            <button className="w-full px-4 py-3 bg-gray-100 hover:bg-gray-200 rounded-lg transition-colors text-left">
              <p className="font-semibold text-[#111111]">Cambiar Contraseña</p>
              <p className="text-sm text-gray-600">Actualiza tu contraseña de acceso</p>
            </button>
            <button className="w-full px-4 py-3 bg-gray-100 hover:bg-gray-200 rounded-lg transition-colors text-left">
              <p className="font-semibold text-[#111111]">Autenticación de Dos Factores</p>
              <p className="text-sm text-gray-600">Añade una capa extra de seguridad</p>
            </button>
            <button className="w-full px-4 py-3 bg-red-50 hover:bg-red-100 rounded-lg transition-colors text-left">
              <p className="font-semibold text-[#D62828]">Cerrar Todas las Sesiones</p>
              <p className="text-sm text-red-600">Cierra sesión en todos los dispositivos</p>
            </button>
          </div>
        </div>
      </div>

      {/* Save Button */}
      <div className="flex justify-end space-x-4">
        <button className="px-6 py-3 bg-gray-100 hover:bg-gray-200 rounded-lg transition-colors">
          Cancelar
        </button>
        <button className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-8 py-3 rounded-lg hover:shadow-lg transition-all">
          Guardar Cambios
        </button>
      </div>
    </div>
  );
}
