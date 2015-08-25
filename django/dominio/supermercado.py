'''
Created on 19/06/2013

@author: dimeglio
'''

class Producto():
    
    def __init__(self,id,nombre,precio,marca,desc='Sin descripcion'):
        self.validacion([id,nombre,precio,marca])
        self.id=id
        self.nombre=nombre
        self.precio=int(precio)
        self.marca=marca
        self.descripcion=desc
     
    def validacion(self,atributos): 
        for atributo in atributos:
            if(atributo == ''):
                raise Exception('Todos los campos son necesarios')

    
class Cliente():
    
    def __init__(self,dinero):
        self.carrito=[]
        self.dinero=dinero
    
    def comprar(self,producto):
        if(self.dinero-producto.precio >= 0):
            self.dinero=self.dinero-producto.precio
            self.carrito.append(producto)
        else:
            raise Exception('Dinero insuficiente')
            
    def getIdsDeProductos(self):
        res=[]
        for producto in self.carrito: 
            res.append(producto.id)
        return res




        