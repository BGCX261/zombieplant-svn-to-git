
from django.http import HttpResponse



def holaMundo(request):
    html = "<html><body>hola mundo!</body></html>" 
    return HttpResponse(html)



