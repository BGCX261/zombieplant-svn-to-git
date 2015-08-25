
from django.template.loader import get_template
from django.template import Context
from django.http import HttpResponse



contador=0

def incrementar(request):
    template= get_template('contador.html')
    global contador
    contador+=1
    html = template.render(Context({'contador':contador}))
    return HttpResponse(html)


