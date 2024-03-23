setup-commitlint:
	cd commitlint && rm -rf .husky && rm -rf node_modules
	cd commitlint && npm install && npm run add-commitlint-git-hook