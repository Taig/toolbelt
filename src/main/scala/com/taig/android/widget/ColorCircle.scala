package com.taig.android.widget

import android.content.Context
import android.graphics.drawable.{Drawable, GradientDrawable}
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import com.taig.android.R
import com.taig.android.graphic.Color

class	ColorCircle( val attributes: AttributeSet = null, val style: Int = 0, val theme: Int = 0 )( implicit context: Context )
extends	ImageView( context, attributes, style, theme )
with	Widget.Styleable
{
	def this( context: Context, attributes: AttributeSet, style: Int, theme: Int ) = this( attributes, style, theme )( context )

	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	private var color: Color = Color.White

	private lazy val check = context
		.getResources
		.getDrawable( R.drawable.color_picker_preference_circle_selected )

	private val circle = context
		.getResources
		.getDrawable( R.drawable.color_picker_preference_circle )
		.mutate()
		.asInstanceOf[GradientDrawable]

	setBackground( circle )
	setColor( color )

	override def setBackground( background: Drawable )
	{
		if( Build.VERSION.SDK_INT >= 16 )
		{
			super.setBackground( background )
		}
		else
		{
			setBackgroundDrawable( background )
		}
	}

	def getColor = color

	def setColor( color: Color )
	{
		this.color = color
		circle.setColor( color )
		invalidate()
	}

	def isActive = getDrawable != null

	def setActive( active: Boolean ) = setImageDrawable( if( active ) check else null )

	def activate() = setActive( true )

	def deactivate() = setActive( false )

	def scale( value: Float )
	{
		circle.setSize( ( circle.getIntrinsicWidth * value ).toInt, ( circle.getIntrinsicHeight * value ).toInt )
	}

	override def onMeasure( widthMeasureSpec: Int, heightMeasureSpec: Int ) =
	{
		setMeasuredDimension( circle.getIntrinsicWidth, circle.getIntrinsicWidth )
	}
}