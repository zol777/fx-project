FROM        adoptopenjdk/openjdk15:alpine-jre
LABEL       app=trade-service
RUN         addgroup --system app && adduser --system --no-create-home --ingroup app app
USER        app
ADD         file/trade-service.tar    /opt
CMD         ["/opt/trade-service/bin/trade-service"]
