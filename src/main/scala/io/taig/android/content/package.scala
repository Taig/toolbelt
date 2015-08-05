package io.taig.android

import android.content.Context

package object content
{
	implicit class	RichContext( val context: Context )
	extends			ops.Context

	implicit class	RichIntent( val intent: android.content.Intent )( implicit val context: Context )
	extends			ops.Intent

	implicit class	RichUnit[T: Numeric]( unit: T )( implicit val context: Context )
	extends			ops.Unit[T]( unit )

	implicit class	RichResource( val resource: Int )( implicit val context: Context )
	extends			ops.Resource
}