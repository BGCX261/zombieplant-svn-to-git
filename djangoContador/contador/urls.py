from django.conf.urls import patterns, include, url
import views as v
# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',
    ('incrementar',v.incrementar),
)
