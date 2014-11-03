package com.taig.android.widget.inline

import android.content.Context
import android.util.AttributeSet
import com.taig.android.widget
import com.taig.android.widget.Inline

class	Spinner( attributes: AttributeSet = null )( implicit context: Context )
extends	Inline( context, attributes )
{
	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	protected lazy val input = new widget.Spinner( attributes )

	def getText = input.getSelectedItem.toString
}