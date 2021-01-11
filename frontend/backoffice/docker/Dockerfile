FROM        adoptopenjdk/openjdk15:alpine-jre
LABEL       app=backoffice
RUN         addgroup --system app && adduser --system --no-create-home --ingroup app app
USER        app
ADD         file/backoffice.tar    /opt
CMD         ["/opt/backoffice/bin/backoffice"]
