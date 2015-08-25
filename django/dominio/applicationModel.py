'''
Created on 20/06/2013

@author: dimeglio
'''
import supermercado as s
import dao as d
from dao import Repositorio
from compiler.ast import Lambda

class SupermercadoModel():
    
    def __init__(self):
        self.cliente=s.Cliente(5000)
        self.busqueda=d.Repositorio.productos
        self.productoSeleccionado=None
        self.criterioDeBusqueda=''
        
    def buscar(self):
        self.busqueda=Repositorio.getProductosDeNombre(self.criterioDeBusqueda)
        self.restarProductos()
        
    def restarProductos(self):
        ids=self.cliente.getIdsDeProductos()
        self.busqueda=filter(lambda a: not a.id in self.cliente.getIdsDeProductos(),self.busqueda)
                
        
    def comprar(self):
        self.cliente.comprar(self.productoSeleccionado)
        self.buscar()
        
    def setProductoSeleccionado(self,id):
        self.productoSeleccionado=Repositorio.getProductoDeId(id)
        
    def setCriterioDeBusqueda(self,criterio):
        self.criterioDeBusqueda=criterio
  
    def agregarProducto(self,producto):
        Repositorio.agregarProducto( producto)
        

        