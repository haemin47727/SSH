ssh-keygen -t ed25519 -C "your_email@example.com" -f ~/.ssh/id_ed25519
//must have filename beginning with id_. Write your email in "your_email@example.com"
cat ~/.ssh/id_ed25519.pub
touch ~/.bash_profile
        //bash.rc wont work if using gitbash in windows. must use bash_profile.
//Dobule click on .bash_profile folder and choose IntelliJ lightsource and paste the following:
        env=~/.ssh/agent.env

agent_load_env () { test -f "$env" && . "$env" >| /dev/null ; }

agent_start () {
    (umask 077; ssh-agent >| "$env")
    . "$env" >| /dev/null ; }

agent_load_env

# agent_run_state: 0=agent running w/ key; 1=agent w/o key; 2=agent not
# running
        agent_run_state=$(ssh-add -l >| /dev/null 2>&1; echo $?)

        if [ ! "$SSH_AUTH_SOCK" ] || [ $agent_run_state = 2 ]; then
echo "Starting ssh-agent and adding your private keys."
agent_start
ssh-add ~/.ssh/id_*[!.pub]
elif [ "$SSH_AUTH_SOCK" ] && [ $agent_run_state = 1 ]; then
echo "Adding your private keys to ssh-agent."
ssh-add ~/.ssh/id_*[!.pub]
fi

unset env
        // ignore "no usages" above
        // close all terminal and open. if you had passphrase it will ask you
        // check all ssh keys are loaded :
ssh-add -l


