FROM ubuntu

# copy script
ADD migrate.sh scripts/migrate.sh

# allow permission
RUN chmod +x scripts/*.sh

ENTRYPOINT ["scripts/migrate.sh"]