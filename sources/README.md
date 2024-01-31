
# Docker:
Helpful commands:
 - run default profile (db only if not set, see below for overrides)
`docker compose up`
 - run everything (without extra config):
`docker compose --profile backend --profile frontend up`
 - only run db (regardless of config)
`docker compose up -d db`
 - to refresh running images
`docker compose restart`
 - to close everything
`docker compose down`

You might want to add a system shortcut with target: `cmd /K docker compose up`
It starts a command line and runs default profile.

And an .env file with one of the following snippets to reconfigure default compose up behaviour
`COMPOSE_PROFILES=backend`
`COMPOSE_PROFILES=frontend`
`COMPOSE_PROFILES=frontend,backend`

# Remote acces to Docker:
 - run Vagrant to start virtual machine:
`vagrant up`
 - reload Vagrant for all changes to take effect:
`vagrant reload`
- configure Docker context on the host machine:
`docker context create context_name --docker “host=tcp://127.0.0.1:2375” --default-stack-orchestrator swarm`
- switch to the created Docker context:
`docker context use context_name`
