from applicationModel import SupermercadoModel
from django.shortcuts import render_to_response
from django.template import RequestContext
from supermercado import Producto


def supermercado(request):
    if not 'supermercadoModel' in request.session:
        request.session['supermercadoModel']=SupermercadoModel()
    return render_to_response('supermercado.html',context_instance=RequestContext(request))

def comprar(request):
    superMercado=request.session['supermercadoModel']
    idProducto = request.GET.get('idProducto', None)
    superMercado.setProductoSeleccionado(idProducto)
    mensaje=None
    try:
        superMercado.comprar()
        mensaje='Compra exitosa'
    except Exception,e:
        mensaje=e
    request.session['supermercadoModel']=superMercado

    return render_to_response('supermercado.html',{'mensaje':mensaje}, context_instance=RequestContext(request))

def buscar(request):
    superMercado=request.session['supermercadoModel']
    criterio = request.GET.get('criterio',superMercado.criterioDeBusqueda)
    superMercado.setCriterioDeBusqueda(criterio)
    superMercado.buscar()
    request.session['supermercadoModel']=superMercado
    return render_to_response('supermercado.html', context_instance=RequestContext(request))

def datos(request):
    superMercado=request.session['supermercadoModel']
    idProducto = request.GET.get('idProducto', None)
    superMercado.setProductoSeleccionado(idProducto)
    return render_to_response('datos.html',{'producto':superMercado.productoSeleccionado}, context_instance=RequestContext(request))
    
def nuevo(request):
    return render_to_response('nuevo.html', context_instance=RequestContext(request))

 
def addNuevo(request):   
    superMercado=request.session['supermercadoModel']
    id= request.GET.get('id',None)
    nombre= request.GET.get('nombre',None)
    precio= request.GET.get('precio',None)
    marca= request.GET.get('marca',None)
    
    try:
        p= Producto(id,nombre,precio,marca)
        superMercado.agregarProducto(p)
        mensaje='EL producto se agrego correctamente'
    except Exception, e:
        mensaje=e
    request.session['supermercadoModel']=superMercado    
    return render_to_response('nuevo.html',{'mensaje':mensaje},context_instance=RequestContext(request))


