## Echo Speaking Partner
This is the main workspace from which we organise the workflow amongst (or currently between) developers.

### Get Started

* clone this repo
        `git clone git@bitbucket.org:ali-abbas-personal/echospeakingpartner.git `

* initialise submodules `git submodule update --init --recursive`

* setup local development environment variables `.\initialise_dev_env.sh`

* initialise docker container `docker-compose -f docker-compose.dev.yml up`

* make sure phoneclient repository is buildable and bundle-able `docker-compose -f docker-compose.dev.yml exec web bash -c "cd phoneclient && ./build.sh"`

* make sure development server is running by navigating to http://127.0.0.1:8000/