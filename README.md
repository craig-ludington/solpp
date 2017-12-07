# solpp - Solidity Preprocessor

Developer tools for working with Solidity programs.

## Functions

### `solpp.import/inline`

Preprocess a Solidity file, inlining all `import`s to make a single `.sol` file.

The result can be copy/pasted into the [Remix](https://remix.ethereum.org/#version=soljson-v0.4.19+commit.c4cbbb05.js) IDE.

#### Example
`(solpp.import/inline "/path/to/my/contracts" "Main.sol")`







