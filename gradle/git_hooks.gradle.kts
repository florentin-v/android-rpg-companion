// Tasks for automatically installing git-hooks and making it executable also.
tasks.register(name = "installGitHooks", type = Copy::class) {
    group = "install"
    description = "Install Git Hooks Scripts in .git/hooks"
    from("./.scripts/pre-commit")
    into("./.git/hooks")
    filePermissions {
        user {
            read = true
            execute = true
        }
        other.execute = false
    }
}
