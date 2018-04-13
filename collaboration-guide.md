## Project organization

As you can see in the ./README.md, our project is organized with directories. Each one has specific files related to the develpment. Here in this file we will present the project workflow. It is based on [GitHub Flow](https://guides.github.com/introduction/flow/) (read this up), with some additional steps added to maintain rastreability of activities.

## Workflow

* An issue is an project activity that needs to be done. One issue is assigned to one or more collaborators.
* Each issue has a branch, that contains changes made to finish the activity. So, once you are assigned to an issue and are going to work on it, create a branch that will contain the changes.
* Once you make all necessary changes, create a Pull Request, where the team will discuss about your changes, and, if necessary, propose changes. In this state, you can continue to push changes to your branch in light of discussion and feedback about your commits.
* After the PR is approved by reviewers, it is ready to be merged to `master`. Once it is, the issue is concluded.

### Issues

All of the project activities are described as issues. Each issue has labels, which caracterize them to provide additional filters. They will also contain a description about the activity - anything necessary for the development of the activity.

### Pull Requests

All PRs must have the issue it solves in the description, written exactly like this:

`Resolves #{ISSUE_ID}`

Also, please provide any necessary info about the solution you choose that you feel is relevant to the reviewers. When approved, selecte the option `Squash and Merge` before merging it with master.

## Naming Conventions

Branches should be all lowercase, separeted with hifens, like this: collaboration-guide, work-breakdown-structure.

Commits should be clear about what they change. Example: "Changing class diagram structure" and not "Updating class diagram".

This file can be changed at any moment according to necessity. Be sure to check if you see any commit regarding this file.
