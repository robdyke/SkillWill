module.exports = [
	{
		test: /\.jsx?$/,
		exclude: /(node_modules|public\/)/,
		loader: 'babel-loader',
	},
	{
		test: /\.css$/,
		loaders: ['style-loader', 'css-loader?importLoaders=1'],
		exclude: /(node_modules|public\/)/,
	},
	{
		test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
		exclude: /(node_modules|public\/)/,
		loader: 'file-loader',
	},
	{
		test: /\.(otf)$/,
		exclude: /(node_modules|public\/)/,
		loader: 'file-loader',
	},
	{
		test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
		exclude: /(node_modules|public\/)/,
		loader: 'url-loader?limit=10000&mimetype=application/octet-stream',
	},
	{
		test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
		exclude: /(node_modules|public\/)/,
		loader: 'url-loader?limit=10000&mimetype=image/svg+xml',
	},
	{
		test: /\.gif/,
		exclude: /(node_modules|public\/)/,
		loader: 'url-loader?limit=10000&mimetype=image/gif',
	},
	{
		test: /\.jpg/,
		exclude: /(node_modules|public\/)/,
		loader: 'url-loader?limit=10000&mimetype=image/jpg',
	},
	{
		test: /\.png/,
		exclude: /(node_modules|public\/)/,
		loader: 'url-loader?limit=10000&mimetype=image/png',
	},
]