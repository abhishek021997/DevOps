From ubuntu
MAINTAINER abhishek_sharma
RUN apt-get update -y && apt install apache2 -y
WORKDIR /var/www/html/
COPY index.html .
ADD https://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_templates_band&stacked=h ./test.html
VOLUME testvol
#USER abhi
EXPOSE 80 8080
#ENTRYPOINT 
CMD ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"]
