from django.conf.urls import patterns, include, url
import views as v
# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',
                      ('super',v.supermercado),
                      ('comprar',v.comprar),
                      ('buscar',v.buscar),
                      ('datos',v.datos),
                      ('nuevo',v.nuevo),
                      ('addNuevo',v.addNuevo),
)
