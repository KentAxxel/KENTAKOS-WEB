import { Plus, Users, Edit, Trash2, Shield, X, Save } from 'lucide-react';
import { useState, useEffect } from 'react';

// Interfaz para el Rol que viene del backend
interface Rol {
  idRol: number;
  nombre: string;
  descripcion: string;
  estado: number;
  permisos?: any[];
}

// Interfaz para el formulario de crear/editar rol
interface RolForm {
  nombre: string;
  descripcion: string;
}

export default function Roles() {
  const [roles, setRoles] = useState<Rol[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [showModal, setShowModal] = useState(false);
  const [editingRol, setEditingRol] = useState<Rol | null>(null);
  const [formData, setFormData] = useState<RolForm>({
    nombre: '',
    descripcion: ''
  });

  // Cargar roles desde el backend
  useEffect(() => {
    cargarRoles();
  }, []);

  const cargarRoles = async () => {
    try {
      setLoading(true);
      setError(null);

      const response = await fetch('https://shop.spring.informaticapp.com/api/admin/roles');


      if (!response.ok) {
        throw new Error('Error al cargar los roles');
      }

      const data = await response.json();
      setRoles(data);
    } catch (err) {
      setError('No se pudieron cargar los roles. Verifica que el backend esté corriendo.');
      console.error('Error:', err);
    } finally {
      setLoading(false);
    }
  };

  // Abrir modal para crear nuevo rol
  const handleNuevoRol = () => {
    setEditingRol(null);
    setFormData({ nombre: '', descripcion: '' });
    setShowModal(true);
  };

  // Abrir modal para editar rol existente
  const handleEditarRol = (rol: Rol) => {
    setEditingRol(rol);
    setFormData({
      nombre: rol.nombre,
      descripcion: rol.descripcion
    });
    setShowModal(true);
  };

  // Guardar rol (crear o actualizar)
  const handleGuardarRol = async () => {
    try {
      const url = editingRol
        ? `/api/admin/roles/${editingRol.idRol}`
        : '/api/admin/roles';

      const method = editingRol ? 'PUT' : 'POST';

      const response = await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error('Error al guardar el rol');
      }

      // Cerrar modal y recargar roles
      setShowModal(false);
      setEditingRol(null);
      cargarRoles();
    } catch (err) {
      console.error('Error:', err);
      alert('Error al guardar el rol');
    }
  };

  // Eliminar rol (borrado lógico)
  const handleEliminarRol = async (id: number) => {
    if (!window.confirm('¿Estás seguro de eliminar este rol?')) {
      return;
    }

    try {
      const response = await fetch(`/api/admin/roles/${id}`, {
        method: 'DELETE'
      });

      if (!response.ok) {
        throw new Error('Error al eliminar el rol');
      }

      // Recargar la lista de roles
      cargarRoles();
    } catch (err) {
      console.error('Error:', err);
      alert('Error al eliminar el rol');
    }
  };

  // Colores para las tarjetas de roles
  const coloresGradiente = [
    'from-[#D62828] to-[#F77F00]',
    'from-[#F77F00] to-[#D62828]',
    'from-[#111111] to-[#F77F00]',
    'from-[#D62828] to-[#111111]',
    'from-[#F77F00] to-[#D62828]',
  ];

  // Estado de carga
  if (loading) {
    return (
      <div className="space-y-6 p-8">
        <div className="animate-pulse">
          <div className="h-8 bg-gray-200 rounded w-1/4 mb-4"></div>
          <div className="h-4 bg-gray-200 rounded w-1/2 mb-8"></div>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {[1, 2, 3].map((i) => (
              <div key={i} className="bg-white rounded-xl p-6 h-48"></div>
            ))}
          </div>
        </div>
      </div>
    );
  }

  // Estado de error
  if (error) {
    return (
      <div className="p-8">
        <div className="bg-red-50 border border-red-200 rounded-lg p-6 text-center">
          <p className="text-red-600 mb-4">{error}</p>
          <button
            onClick={cargarRoles}
            className="bg-red-600 text-white px-6 py-2 rounded-lg hover:bg-red-700"
          >
            Reintentar
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold text-[#111111]">Roles</h1>
          <p className="text-gray-600">Gestión de roles y accesos del sistema</p>
        </div>
        <button
          onClick={handleNuevoRol}
          className="bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-6 py-3 rounded-lg hover:shadow-lg transition-all flex items-center space-x-2"
        >
          <Plus className="w-5 h-5" />
          <span>Nuevo Rol</span>
        </button>
      </div>

      {/* Roles Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {roles.length === 0 ? (
          <div className="col-span-full text-center py-12 text-gray-500">
            No hay roles registrados. ¡Crea el primero!
          </div>
        ) : (
          roles.map((rol, index) => (
            <div
              key={rol.idRol}
              className="bg-white rounded-xl shadow-md border border-gray-100 overflow-hidden hover:shadow-xl transition-all"
            >
              {/* Header with gradient */}
              <div className={`h-24 bg-gradient-to-br ${coloresGradiente[index % coloresGradiente.length]} flex items-center justify-center`}>
                <Shield className="w-12 h-12 text-white" />
              </div>

              {/* Content */}
              <div className="p-6">
                <h3 className="text-xl font-bold text-[#111111] mb-2">{rol.nombre}</h3>
                <p className="text-gray-600 text-sm mb-4">{rol.descripcion}</p>

                <div className="flex items-center space-x-2 text-sm text-gray-500 mb-6">
                  <Users className="w-4 h-4" />
                  <span>Rol {rol.estado === 1 ? 'Activo' : 'Inactivo'}</span>
                </div>

                {/* Actions */}
                <div className="flex gap-2">
                  <button
                    onClick={() => handleEditarRol(rol)}
                    className="flex-1 flex items-center justify-center space-x-2 px-4 py-2 bg-gray-100 hover:bg-gray-200 rounded-lg transition-colors"
                  >
                    <Edit className="w-4 h-4" />
                    <span className="text-sm">Editar</span>
                  </button>
                  <button
                    onClick={() => handleEliminarRol(rol.idRol)}
                    className="flex items-center justify-center px-4 py-2 bg-red-50 hover:bg-red-100 text-[#D62828] rounded-lg transition-colors"
                  >
                    <Trash2 className="w-4 h-4" />
                  </button>
                </div>
              </div>
            </div>
          ))
        )}
      </div>

      {/* Modal para Crear/Editar Rol */}
      {showModal && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-white rounded-xl p-6 w-full max-w-md mx-4">
            <div className="flex justify-between items-center mb-6">
              <h2 className="text-2xl font-bold text-[#111111]">
                {editingRol ? 'Editar Rol' : 'Nuevo Rol'}
              </h2>
              <button
                onClick={() => setShowModal(false)}
                className="p-2 hover:bg-gray-100 rounded-lg"
              >
                <X className="w-5 h-5" />
              </button>
            </div>

            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Nombre del Rol
                </label>
                <input
                  type="text"
                  value={formData.nombre}
                  onChange={(e) => setFormData({ ...formData, nombre: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-[#D62828] focus:border-transparent"
                  placeholder="Ej: Administrador"
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Descripción
                </label>
                <textarea
                  value={formData.descripcion}
                  onChange={(e) => setFormData({ ...formData, descripcion: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-[#D62828] focus:border-transparent"
                  rows={3}
                  placeholder="Descripción del rol..."
                />
              </div>
            </div>

            <div className="flex gap-3 mt-6">
              <button
                onClick={() => setShowModal(false)}
                className="flex-1 px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-50"
              >
                Cancelar
              </button>
              <button
                onClick={handleGuardarRol}
                className="flex-1 bg-gradient-to-r from-[#D62828] to-[#F77F00] text-white px-4 py-2 rounded-lg hover:shadow-lg flex items-center justify-center space-x-2"
              >
                <Save className="w-4 h-4" />
                <span>{editingRol ? 'Actualizar' : 'Crear'}</span>
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Info Card */}
      <div className="bg-gradient-to-r from-[#D62828]/10 to-[#F77F00]/10 rounded-xl p-6 border border-[#D62828]/20">
        <div className="flex items-start space-x-4">
          <div className="w-12 h-12 bg-gradient-to-br from-[#D62828] to-[#F77F00] rounded-lg flex items-center justify-center flex-shrink-0">
            <Shield className="w-6 h-6 text-white" />
          </div>
          <div>
            <h3 className="font-bold text-[#111111] mb-2">Acerca de los Roles</h3>
            <p className="text-gray-600 text-sm">
              Los roles definen los niveles de acceso y permisos dentro del sistema. Cada usuario debe tener un rol asignado para poder acceder a las funcionalidades correspondientes. Puedes crear roles personalizados según las necesidades de tu restaurante.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}