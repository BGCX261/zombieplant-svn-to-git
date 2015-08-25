'''
Created on 20/06/2013

@author: dimeglio
'''
from supermercado import Producto

class Repositorio():
    
    productos=[Producto('1',"Televisor",2000,"Philips"),
               Producto('2',"Microondas",1200,"Sanyo"),
               Producto('3',"Heladera",5000,"Sanyo")]
    
    @classmethod  
    def getProductoDeId(self,id):
        for producto in self.productos:
            if(producto.id == id):
                return producto
        raise Exception('el producto es invalido')
       
    @classmethod      
    def getProductosDeNombre(self,nombre):
        if(nombre == ''):
            return self.productos
        productos=[]
        for producto in self.productos:
            if(producto.nombre.lower().startswith(nombre.lower())):
                productos.append(producto)
             
        return productos
    
    @classmethod  
    def agregarProducto(self,producto):
        self.validacion(producto.id)
        self.productos.append(producto)
    
    @classmethod     
    def validacion(self,id):
        for producto in self.productos:
            if(producto.id == id):
                raise Exception('El id esta en uso')
        
        
 
        
                