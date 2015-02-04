package com.taig.android.content.fragment

import android.os.Bundle
import com.taig.android.concurrent.Ui
import com.taig.android.content.Fragment

import scala.collection.mutable

/**
 * A job queue that schedules its jobs for when the fragment is safe to use for Ui or transaction changes
 * 
 * All jobs will be automatically executed on the Ui thread
 */
trait	Jobs
extends	Fragment
{
	private var ready = false

	private lazy val queue = mutable.Queue.empty[() => Unit]

	override def onCreate( state: Option[Bundle] ) =
	{
		super.onCreate( state )

		setRetainInstance( true )
	}

	override def onStart() = synchronized
	{
		super.onStart()

		ready = true

		Ui( queue.dequeueAll( _ => true ).foreach( _() ) )
	}

	override def onPause() = synchronized
	{
		super.onPause()

		ready = false
	}

	/**
	 * Do it now or as soon as the Fragment is (re-) starting
	 */
	def schedule( job: => Unit ) = synchronized( if( ready ) job else queue.enqueue( () => job ) )

	/**
	 * Do it now or not at all
	 */
	def attempt( job: => Unit ) = synchronized( if( ready ) job )
}