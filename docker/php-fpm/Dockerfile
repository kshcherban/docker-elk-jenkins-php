FROM php:7.1-fpm-alpine

RUN apk add --no-cache --update pcre-dev ${PHPIZE_DEPS} \
    && pecl install redis-3.1.6 \
    && docker-php-ext-enable redis \
    && apk del pcre-dev ${PHPIZE_DEPS}

COPY php.ini /usr/local/etc/php/php.ini

COPY *.php /var/www/html/
